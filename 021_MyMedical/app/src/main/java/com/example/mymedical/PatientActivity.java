package com.example.mymedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class PatientActivity extends AppCompatActivity {
    String patientId;
    PatientProfileFragment patientProfileFragment = new PatientProfileFragment();
    PatientPrescriptionFragment patientPrescriptionFragment = new PatientPrescriptionFragment();
    PatientReportFragment patientReportFragment = new PatientReportFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            patientId = intent.getStringExtra("id");
        }
        patientProfileFragment.setPatientId(patientId);
        patientPrescriptionFragment.setPatientId(patientId);
        patientReportFragment.setPatientId(patientId);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        getSupportFragmentManager().beginTransaction().replace(R.id.patientFrameLayout, patientProfileFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientFrameLayout, patientProfileFragment).commit();
                    return true;
                } else if (id==R.id.prescription) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientFrameLayout, patientPrescriptionFragment).commit();
                    return true;
                } else if (id==R.id.report) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientFrameLayout, patientReportFragment).commit();
                    return true;
                }else {
                    return false;
                }
            }
        });
    }
}