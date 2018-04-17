package com.jogazo.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class SimpleGet extends AppCompatActivity {
    private static final String LOGTAG = "NetworkExplorer";
    DownloadManager downloadManager;

    private EditText getInput;
    private Button getButton;
    private String inputter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_get);

        this.getInput = (EditText) findViewById(R.id.get_input);
        this.getButton = (Button) findViewById(R.id.get_button);
        inputter = getInput.getText().toString();
        Log.i(LOGTAG, "inputter: " + inputter);

        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Log.d(LOGTAG, "I have downloadManager");

        this.getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputter = getInput.getText().toString();
                Log.i(LOGTAG, "inputter: " + inputter);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(inputter));

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("thinkbroadband zip");
                request.setDescription("Downloading " + "zip");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir("/Download/", "5MB.zip");

                downloadManager.enqueue(request);
            }
        });
    }

}

