package com.example.medicaladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button doctorChq, patientChq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctorChq = findViewById(R.id.doctorChq);
        patientChq = findViewById(R.id.patientChq);

        doctorChq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DoctorChqActivity.class);
                startActivity(myIntent);
            }
        });

        patientChq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, PatientChqActivity.class);
                startActivity(myIntent);
            }
        });
    }
}