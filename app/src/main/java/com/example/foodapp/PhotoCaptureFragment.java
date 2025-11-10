package com.example.foodapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executors;

public class PhotoCaptureFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int STORAGE_PERMISSION_CODE = 123;
    AppViewModel viewModel;

    private ImageView imageView;
    private Button buttonGallery, buttonCamera, buttonSave, back;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_capture, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        imageView = view.findViewById(R.id.imageView);
        buttonGallery = view.findViewById(R.id.buttonGallery);
        buttonCamera = view.findViewById(R.id.buttonCamera);
        buttonSave = view.findViewById(R.id.buttonSave);
        back = view.findViewById(R.id.imageCaptureBack);

        buttonGallery.setText("Select From Gallery");
        buttonCamera.setText("Take Picture");
        buttonSave.setText("Save");
        back.setText("Back");

        // Request storage permission on start
        requestStoragePermission();

        // Open Gallery to select image
        buttonGallery.setOnClickListener(v -> openGallery());

        // Open Camera to capture image
        buttonCamera.setOnClickListener(v -> openCamera());

        // Save image to database
        buttonSave.setOnClickListener(v -> saveImageToDatabase());

        // Navigate to PhotoListFragment
        back.setOnClickListener(v ->
                viewModel.setFragmentClicked(1)
                );

        return view;
    }

    // Request storage permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission granted to write external storage", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permission denied to write external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    // Navigate to PhotoListFragment


    // Save the image to the database
    private void saveImageToDatabase() {
        if (imageUri != null) {
            MealImage meal = new MealImage(imageUri.toString());
            Executors.newSingleThreadExecutor().execute(() -> {
                MealImageDatabase.getDatabase(getContext()).mealImageDao().insert(meal);
                getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Image saved to database", Toast.LENGTH_SHORT).show());
            });
        } else {
            Toast.makeText(getContext(), "No image to save", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && data != null) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            } else if (requestCode == CAMERA_REQUEST_CODE && data.getExtras() != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                // Save the image to gallery and get URI
                imageUri = getImageUriFromBitmap(bitmap);
                if (imageUri == null) {
                    Toast.makeText(getContext(), "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Helper function to get URI from a bitmap image
    private Uri getImageUriFromBitmap(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "New Photo", null);
        if (path != null) {
            return Uri.parse(path);
        } else {
            Toast.makeText(getContext(), "Failed to insert image into gallery", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
