package com.dearmahmud.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView txView;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txView = findViewById(R.id.txView);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String str = sharedPreferences.getString("name","Default Value");
        txView.setText(str);
    }
}