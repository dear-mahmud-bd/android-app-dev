package com.dearmahmud.visibility;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button bShow, bGone, bHide, layoutGone, bToast;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        bShow = findViewById(R.id.bShow);
        bGone = findViewById(R.id.bGone);
        bHide = findViewById(R.id.bHide);
        bToast = findViewById(R.id.bToast);
        layoutGone = findViewById(R.id.layoutGone);
        mainLayout = findViewById(R.id.mainLayout);

        layoutGone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(myIntent);
                Toast.makeText(MainActivity.this, "Hello! how are you..?", Toast.LENGTH_SHORT).show();
                // mainLayout.setVisibility(View.GONE);
            }
        });
        bGone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.GONE);
            }
        });
        bHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.INVISIBLE);
            }
        });
        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.VISIBLE);
            }
        });
        bToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello! how are you..?", Toast.LENGTH_SHORT).show();
            }
        });
    }
}