package com.dearmahmud.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BmiActivity3 extends AppCompatActivity {
    EditText weight, height;
    Button bmiBtn;
    TextView showBmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi3);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmiBtn = findViewById(R.id.bmiBtn);
        showBmi = findViewById(R.id.showBmi);

        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight.getText().toString().isEmpty() || height.getText().toString().isEmpty()){
                    Toast.makeText(BmiActivity3.this, "Height and Weight both field are required", Toast.LENGTH_SHORT).show();
                }else{
                    float w = Float.parseFloat(weight.getText().toString());
                    float h = Float.parseFloat(height.getText().toString()) / 100;
                    float bmi = w/(h*h);
                    showBmi.setText("BMI = "+String.format("%.2f", bmi));
                }
            }
        });
    }
}