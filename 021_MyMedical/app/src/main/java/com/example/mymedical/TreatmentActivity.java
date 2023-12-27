package com.example.mymedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TreatmentActivity extends AppCompatActivity {
    AddPrescriptionFragment addPrescriptionFragment = new AddPrescriptionFragment();
    AddReportFragment addReportFragment = new AddReportFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        BottomNavigationView treatmentBottomNavi = findViewById(R.id.treatmentBottomNavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.patientTreatmentFrameLayout, addPrescriptionFragment).commit();

        treatmentBottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.addPrescription) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientTreatmentFrameLayout, addPrescriptionFragment).commit();
                    return true;
                } else if (id==R.id.addReport) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.patientTreatmentFrameLayout, addReportFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}