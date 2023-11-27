package com.dearmahmud.web_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    WebView webApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webApp = findViewById(R.id.webApp);
        webApp.getSettings().setJavaScriptEnabled(true);
        // This portion have
        webApp.loadUrl("https://www.youtube.com/watch?v=B9XVceZiZhg");
    }
}