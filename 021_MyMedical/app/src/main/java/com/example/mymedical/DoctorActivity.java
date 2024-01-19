package com.example.mymedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorActivity extends AppCompatActivity {
    String doctorId;
    DoctorProfileFragment doctorProfileFragment = new DoctorProfileFragment();
    DoctorFindPatientFragment doctorFindPatientFragment = new DoctorFindPatientFragment();
    SharedPreferences sharedPreferencesD;
    SharedPreferences.Editor editorD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        sharedPreferencesD = getSharedPreferences("Loginfileasdoctor", Context.MODE_PRIVATE);
        editorD = sharedPreferencesD.edit();

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            doctorId = intent.getStringExtra("id");
        } else {
            doctorId = sharedPreferencesD.getString("profileD", "");
        }
        doctorProfileFragment.setDoctorId(doctorId);
        doctorFindPatientFragment.setDoctorId(doctorId);


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