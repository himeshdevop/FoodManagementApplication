package com.example.foodapp;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteUtilities {
    private static RemoteUtilities instance;
    private Activity uiActivity;

    private RemoteUtilities(Activity activity) {
        this.uiActivity = activity;
    }

    public static RemoteUtilities getInstance(Activity activity) {
        if (instance == null) {
            instance = new RemoteUtilities(activity);
        }
        return instance;
    }

    public HttpURLConnection openConnection(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-app-id", "1eb5769e");
            connection.setRequestProperty("x-app-key", "e1a17049ea92abc977072966e4483f2e"); // Replace with your App Key
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeRequestBody(HttpURLConnection connection, String jsonInputString) {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnectionOkay(HttpURLConnection connection) {
        try {
            return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getResponseString(HttpURLConnection connection) {
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line.trim());
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}