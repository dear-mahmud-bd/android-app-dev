package com.example.mymedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MedicalHistoryActivity extends AppCompatActivity {
    String paitentId;
    PatientPrescriptionFragment patientPrescriptionFragment = new PatientPrescriptionFragment();
    PatientReportFragment patientReportFragment = new PatientReportFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        BottomNavigationView bottomHistoryNavi = findViewById(R.id.bottomHistoryNavi);
        Intent intent = getIntent();
        if (intent.hasExtra("paitentId")) { paitentId = intent.getStringExtra("paitentId"); }

        patientReportFragment.setPatientId(paitentId);
        patientPrescriptionFragment.setPatientId(paitentId);

        getSupportFragmentManager().beginTransaction().replace(R.id.patientHistoryFrameLayout, patientPrescriptionFragment).commit();
        bottomHistoryNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.historyPrescription) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientHistoryFrameLayout, patientPrescriptionFragment).commit();
                    return true;
                } else if (id==R.id.historyReport) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientHistoryFrameLayout, patientReportFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}