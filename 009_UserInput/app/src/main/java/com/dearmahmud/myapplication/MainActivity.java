package com.dearmahmud.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inptTxt;
    Button clicked, lavLos, bmi;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inptTxt = findViewById(R.id.inptTxt);
        clicked = findViewById(R.id.clicked);
        lavLos = findViewById(R.id.lavLos);
        bmi = findViewById(R.id.bmi);
        display = findViewById(R.id.display);

        clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inptTxt.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Name is required!", Toast.LENGTH_SHORT).show();
                }else{
                    display.setText("Hello, "+name+". How are you?");
                }
            }
        });

        lavLos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(myIntent);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, BmiActivity3.class);
                startActivity(myIntent);
            }
        });
    }
}