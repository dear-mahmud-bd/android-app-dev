package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavi = findViewById(R.id.bottomNavi);
        bottomNavi.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                    
                } else if (item.getItemId()==R.id.noti) {
                    
                } else if (item.getItemId()==R.id.profile) {
                    
                }
            }
        });
        bottomNavi.getOrCreateBadge(R.id.noti).setNumber(4);
        bottomNavi.getOrCreateBadge(R.id.noti).clearNumber();
    }
}