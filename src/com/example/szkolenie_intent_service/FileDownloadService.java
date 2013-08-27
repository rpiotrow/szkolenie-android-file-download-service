package com.example.szkolenie_intent_service;

import android.app.IntentService;
import android.content.Intent;

public class FileDownloadService extends IntentService {

	public static final String INTENT_LOCAL_FILE_NAME = "local_file_name";
	public static final String FILE_DOWNLOADED = "FILE_DOWNLOADED";

	public FileDownloadService() {
		super("FILE_DOWNLOAD_SERVICE");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		FileDownloader fileDownloader = new FileDownloader(this);
		String localFileName = fileDownloader.downloadFile(intent.getStringExtra(MyActivity.INTENT_EXTRA_FILE_URL));
		
		Intent resultIntent = new Intent(FILE_DOWNLOADED);
		resultIntent.putExtra(INTENT_LOCAL_FILE_NAME, localFileName);
		sendBroadcast(resultIntent);
	}

}
