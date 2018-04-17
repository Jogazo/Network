package com.jogazo.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class SimpleGet extends AppCompatActivity {
    private static final String LOGTAG = "NetworkExplorer";
    private DownloadManager downloadManager;

    private TextView getOutput;
    private String inputter = "";
    private String fileName = "_";

/*
    You can launch via:
    am start -a android.intent.action.VIEW -n com.jogazo.network/com.jogazo.network.SimpleGet -e theurl "https://fredvdvd.github.io/test1Mb.db" -e filename "21h46.db"
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_get);

        this.getOutput = (TextView) findViewById(R.id.get_output);

        if (getIntent().hasExtra("theurl")) {
            inputter = getIntent().getStringExtra("theurl");
            Log.i(LOGTAG, "Got extra argument: " + inputter);

            getOutput.setText(inputter);

            if (getIntent().hasExtra("filename")){
                fileName = getIntent().getStringExtra("filename");
                Log.i(LOGTAG, "Got filename argument: " + fileName);
            }
        }
        else {
            Log.w(LOGTAG, "No valid URL was given!");
        }

        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Log.d(LOGTAG, "I have downloadManager");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (inputter != "") {
            doDownload(inputter, fileName);
        }
        else {
            Log.w(LOGTAG, "CANNOT DOWNLOAD!");
            finish();
        }
    }

    void doDownload(String url, String fileName){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(true);
        request.setTitle(fileName);
        request.setDescription("Downloaded via adb am start");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir("/Download/", fileName);

        downloadManager.enqueue(request);
    }
}
