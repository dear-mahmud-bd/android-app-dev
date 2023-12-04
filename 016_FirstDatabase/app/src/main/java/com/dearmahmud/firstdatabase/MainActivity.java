package com.dearmahmud.firstdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tid, tname, tdept, tsession;
    Button button, deleteButton;
    DBHandler dbHandler;
    TextView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tid = findViewById(R.id.tid);
        tname = findViewById(R.id.tname);
        tdept = findViewById(R.id.tdept);
        tsession = findViewById(R.id.tsession);
        displayData = findViewById(R.id.displayData);
        button = findViewById(R.id.button);
        deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tidValue = tid.getText().toString();
                String tnameValue = tname.getText().toString();
                String tdeptValue = tdept.getText().toString();
                String tsessionValue = tsession.getText().toString();
                if(tidValue.isEmpty() || tnameValue.isEmpty() || tdeptValue.isEmpty() || tsessionValue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all data", Toast.LENGTH_SHORT).show();
                }else{
                    dbHandler.addData(tidValue, tnameValue, tdeptValue, tsessionValue);
                    Toast.makeText(MainActivity.this, "Data Added Successfully ^_^", Toast.LENGTH_SHORT).show();
                    tid.setText("");
                    tname.setText("");
                    tdept.setText("");
                    tsession.setText("");
                    displayAllData();
                }
            }
        });
        displayAllData();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteAllData();
                displayAllData(); // Refresh displayed data after deletion
                Toast.makeText(MainActivity.this, "All data deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displayAllData() {
            displayData.setText(dbHandler.getAllData())
        ;
    }
}