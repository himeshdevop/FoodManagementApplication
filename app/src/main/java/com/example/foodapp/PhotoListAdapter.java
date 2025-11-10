package com.example.foodapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    private Context context; // Context for showing Toast messages
    private List<MealImage> mealList = new ArrayList<>();
    private  AppViewModel appViewModel;
    // Constructor to initialize context
    public PhotoListAdapter(Context context, AppViewModel appViewModel) {
        this.context = context;
        this.appViewModel = appViewModel;
    }



    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {

        MealImage meal = mealList.get(position);
        Uri imageUri = Uri.parse(meal.getImageUri());
        holder.imageView.setImageURI(imageUri);

        Button uploadButton = holder.buttonUploadCloud;
        Button copyLinkButton = holder.copy;


        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToFirebase(imageUri, holder.getAbsoluteAdapterPosition());
            }
        });


        copyLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealImage mealImage = mealList.get(holder.getAbsoluteAdapterPosition()+1);
                String url = mealImage.getCloudUrl();


                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Cloud Link", url);
                clipboard.setPrimaryClip(clip);


                Log.d("CopyCloudLink", "Cloud link copied: " + url);


                Toast.makeText(context, "Cloud link copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImageToFirebase(Uri imageUri, int position) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();


        String fileName = "images/" + System.currentTimeMillis() + ".jpg";
        StorageReference imageRef = storageRef.child(fileName);


        UploadTask uploadTask = imageRef.putFile(imageUri);


        uploadTask.addOnSuccessListener(taskSnapshot -> {

            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {

                String downloadUrl = uri.toString();
              
                Toast.makeText(context, "Image uploaded successfully! URL: " + downloadUrl, Toast.LENGTH_LONG).show();

                Executors.newSingleThreadExecutor().execute(() -> {
                    MealImage mealImage = MealImageDatabase.getDatabase(context).mealImageDao().getMealById(position + 1);

                    MealImageDatabase.getDatabase(context).mealImageDao().updateMealById(position+1, downloadUrl);

                });



            }).addOnFailureListener(e -> {
                Toast.makeText(context, "Failed to get download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }

    // Update the dataset and notify the adapter
    public void setMeals(List<MealImage> meals) {
        this.mealList = meals;
        notifyDataSetChanged();
    }


    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button buttonUploadCloud;
        Button copy;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the ImageView in the item layout
            imageView = itemView.findViewById(R.id.imageView);
            buttonUploadCloud = itemView.findViewById(R.id.buttonUploadCloud);
            copy = itemView.findViewById(R.id.buttonCopyCloudLink);
        }


    }
}
