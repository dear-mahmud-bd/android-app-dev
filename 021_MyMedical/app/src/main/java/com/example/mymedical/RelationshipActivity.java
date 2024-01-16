package com.example.mymedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class RelationshipActivity extends AppCompatActivity {
    String paitentId, doctorId;
    TextView textPatientID, textName, textDOB, textBloodType, textFamilyDisease;
    ProgressBar profileProgressBar;
    Button medicalHistory, treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);

        treatment = findViewById(R.id.treatment);
        medicalHistory = findViewById(R.id.medicalHistory);
        profileProgressBar = findViewById(R.id.profileProgressBar);
        textPatientID = findViewById(R.id.textPatientID);
        textName = findViewById(R.id.textName);
        textDOB = findViewById(R.id.textDOB);
        textBloodType = findViewById(R.id.textBloodType);
        textFamilyDisease = findViewById(R.id.textFamilyDisease);

        Intent intent = getIntent();
        if (intent.hasExtra("doctorId")) { doctorId = intent.getStringExtra("doctorId"); }
        if (intent.hasExtra("paitentId")) { paitentId = intent.getStringExtra("paitentId"); }

        RequestQueue queue = Volley.newRequestQueue(RelationshipActivity.this);
        profileProgressBar.setVisibility(View.VISIBLE);
        String url = "https://boom-test.000webhostapp.com/my-medical/patient_profile.php?paitent_id="+paitentId;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("serverResponse", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String patientName = jsonObject.getString("name");
                    String dob = jsonObject.getString("birth");
                    String bloodType = jsonObject.getString("blood");
                    String familyDisease = jsonObject.getString("family_diseas");

                    textPatientID.setText("Patient ID: " + paitentId);
                    textName.setText("Name: " + patientName);
                    textDOB.setText("Birth[Y-M-D]: " + dob);
                    textBloodType.setText("Blood Type: " + bloodType);
                    textFamilyDisease.setText("Family Disease: " + familyDisease);

                    profileProgressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    profileProgressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textName.setText("Error! Try Again");
            }
        });
        queue.add(stringRequest);





        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RelationshipActivity.this, TreatmentActivity.class);
                myIntent.putExtra("paitentId", paitentId);
                myIntent.putExtra("doctorId", doctorId);
                startActivity(myIntent);
            }
        });
        medicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RelationshipActivity.this, MedicalHistoryActivity.class);
                myIntent.putExtra("paitentId", paitentId);
                startActivity(myIntent);
            }
        });

    }
}