package com.example.szkolenie_intent_service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {


	public static final String INTENT_EXTRA_FILE_URL = "file_url";
	private TextView fileContentView;
    private EditText urlInput;
    private Button goButton;
	private FileDowloadBroadcastReceiver broadcastReceiver;

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
    
    @Override
    protected void onStart() {
    	super.onStart();
    	broadcastReceiver = new FileDowloadBroadcastReceiver();
    	registerReceiver(broadcastReceiver, new IntentFilter(FileDownloadService.FILE_DOWNLOADED));
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	unregisterReceiver(broadcastReceiver);
    }

    private void downloadFile(String text) {
        Intent intent = new Intent(this, FileDownloadService.class);
        intent.putExtra(INTENT_EXTRA_FILE_URL, text);
        startService(intent);
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


    private class FileDowloadBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String localFileName = intent.getStringExtra(FileDownloadService.INTENT_LOCAL_FILE_NAME);
			readFileAndDisplayContent(localFileName);
		}

	}

}
