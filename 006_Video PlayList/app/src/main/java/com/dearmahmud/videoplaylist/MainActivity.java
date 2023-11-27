package com.dearmahmud.videoplaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout content1, content2, content3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content1 = findViewById(R.id.content1);
        content2 = findViewById(R.id.content2);
        content3 = findViewById(R.id.content3);

        content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayers.videoURL = "https://www.youtube.com/embed/2NkMNpFeyJM";
                Intent myIntent = new Intent(MainActivity.this, VideoPlayers.class);
                startActivity(myIntent);
            }
        });
        content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayers.videoURL = "https://www.youtube.com/embed/_N6j8HXPt2U";
                Intent myIntent = new Intent(MainActivity.this, VideoPlayers.class);
                startActivity(myIntent);
            }
        });
        content3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayers.videoURL = "https://www.youtube.com/embed/8cySIOcTSEQ";
                Intent myIntent = new Intent(MainActivity.this, VideoPlayers.class);
                startActivity(myIntent);
            }
        });
    }
}