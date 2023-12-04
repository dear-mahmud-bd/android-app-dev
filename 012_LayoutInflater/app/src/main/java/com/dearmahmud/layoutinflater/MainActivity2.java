package com.dearmahmud.layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout lLay, rootLay;
    Button add, remove;
    LayoutInflater layoutInflater;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rootLay = findViewById(R.id.rootLay);
        lLay = findViewById(R.id.lLay);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);

        autoCall();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCall();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setText("Add Hoya Gese");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lLay.removeAllViews();
            }
        });
    }

    void autoCall(){
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView = layoutInflater.inflate(R.layout.new_layout2, lLay);
        imageView = myView.findViewById(R.id.imageView);
    }
}