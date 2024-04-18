package com.example.medicaladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AuthorizeDoctorActivity extends AppCompatActivity {
    String dIdValue;
    EditText doctorId, degree, speciality;
    ProgressBar dProgressBar;
    Button dbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize_doctor);

        dIdValue = getIntent().getStringExtra("ID_EXTRA");
        doctorId = findViewById(R.id.doctorId);
        degree = findViewById(R.id.degree);
        speciality = findViewById(R.id.speciality);
        dProgressBar = findViewById(R.id.dProgressBar);
        dbutton = findViewById(R.id.dbutton);
        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = doctorId.getText().toString() +dIdValue;
                String de = degree.getText().toString();
                String sp = speciality.getText().toString();
                if(doctorId.getText().toString().isEmpty() || de.isEmpty() || sp.isEmpty()){
                    Toast.makeText(AuthorizeDoctorActivity.this, "All of input field are required", Toast.LENGTH_SHORT).show();
                }else{
                    String url = "https://boom-test.000webhostapp.com/my-medical/authorize_doctor.php?id="+dIdValue+ "&doctor_id="+id+ "&degree="+de+ "&speciality="+sp ;
                    RequestQueue queue = Volley.newRequestQueue(AuthorizeDoctorActivity.this);
                    dProgressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    dProgressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(AuthorizeDoctorActivity.this)
                                            .setTitle("Response")
                                            .setMessage(response)
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Return to the parent activity and reload it
                                                    Intent intent = new Intent(AuthorizeDoctorActivity.this, DoctorChqActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    finish(); // Finish the current activity
                                                }
                                            })
                                            .setCancelable(false)
                                            .show();

                                    doctorId.setText("");
                                    degree.setText("");
                                    speciality.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            dProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);

                }
            }
        });
    }
}