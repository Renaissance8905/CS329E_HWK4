package com.example.cjs2599.cs329e_hwk4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.content.Intent;

import java.net.URL;


public class BuildingInfoActivity extends AppCompatActivity {

    public WebView webView;
    public String incomingURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_info);

        incomingURL = getIntent().getData().toString();

        webView = (WebView) findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        webView.loadUrl(incomingURL);

        if(incomingURL != null){
            Log.i("intent", "Intent Received");
            Log.i("intent", incomingURL);

        } else {
            Log.i("intent", "Intent NOT Received");

        }

    }
}
