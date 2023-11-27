package com.dearmahmud.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3;
    ImageView linkImg;
    LottieAnimationView codingView;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(myIntent);
            }
        });

        linkImg = findViewById(R.id.linkImg);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    flag = 1;
                    Picasso.get()
                            .load("https://png.pngtree.com/png-clipart/20200224/original/pngtree-cartoon-color-simple-male-avatar-png-image_5230557.jpg")
                            .placeholder(R.drawable.loading)
                            .into(linkImg);
                } else {
                    flag = 0;
                    Picasso.get()
                            .load("https://icon-library.com/images/avatar-icon-images/avatar-icon-images-4.jpg")
                            .placeholder(R.drawable.loading)
                            .into(linkImg);
                }
            }
        });

        codingView = findViewById(R.id.codingView);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                codingView.setAnimation(R.raw.coding_1);
                codingView.playAnimation();
                codingView.loop(true);
            }
        });
    }
}