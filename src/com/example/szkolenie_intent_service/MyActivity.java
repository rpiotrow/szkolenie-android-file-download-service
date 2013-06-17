package com.example.szkolenie_intent_service;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class MyActivity extends Activity {

    private TextView fileContentView;
    private EditText urlInput;
    private Button goButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fileContentView = (TextView) findViewById(R.id.content);
        urlInput = (EditText) findViewById(R.id.url_input);
        goButton = (Button) findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(urlInput.getText().toString());
            }
        });
    }

    private void downloadFile(String text) {
        //TODO: zaimeplemntuj mnie
    }

    /**
     * Otwiera plik, którego nazwa zwracana jest przez FileDownloader
     * @param fileName
     */
    private void readFileAndDisplayContent(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();
            fileContentView.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
