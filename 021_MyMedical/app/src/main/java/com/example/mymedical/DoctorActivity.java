package com.example.mymedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorActivity extends AppCompatActivity {
    DoctorProfileFragment doctorProfileFragment = new DoctorProfileFragment();
    DoctorFindPatientFragment doctorFindPatientFragment = new DoctorFindPatientFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        BottomNavigationView doctorBottomNavi = findViewById(R.id.doctorBottomNavi);

        getSupportFragmentManager().beginTransaction().replace(R.id.doctorFrameLayout, doctorProfileFragment).commit();

        doctorBottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.drProfile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.doctorFrameLayout, doctorProfileFragment).commit();
                    return true;
                } else if (id==R.id.findPatient) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.doctorFrameLayout, doctorFindPatientFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}