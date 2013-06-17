package com.example.szkolenie_intent_service;

import android.content.Context;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

    public static final String FILE_NAME = "data";
    private final Context ctx;

    public FileDownloader(Context ctx) {
        this.ctx = ctx;
    }

    public String downloadFile(String address) {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            readData(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return FILE_NAME;
    }

    private void readData(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        FileOutputStream outputDataStream = ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

        String line;
        while((line = reader.readLine()) != null) {
            outputDataStream.write(line.getBytes());
            outputDataStream.write("\n".getBytes());
        }
        reader.close();
        outputDataStream.close();
    }
}
