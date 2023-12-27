package com.example.mymedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class RelationshipActivity extends AppCompatActivity {
    DoctorPatientFragment doctorPatientFragment = new DoctorPatientFragment();
    Button medicalHistory, treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);

        treatment = findViewById(R.id.treatment);
        medicalHistory = findViewById(R.id.medicalHistory);

        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelationshipActivity.this, TreatmentActivity.class));
            }
        });

        medicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelationshipActivity.this, MedicalHistoryActivity.class));
            }
        });

    }
}