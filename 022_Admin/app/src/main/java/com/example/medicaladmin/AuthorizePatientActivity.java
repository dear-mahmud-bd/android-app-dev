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

public class AuthorizePatientActivity extends AppCompatActivity {
    String idValue;
    EditText patientId, family;
    ProgressBar uprogressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize_patient);

        idValue = getIntent().getStringExtra("ID_EXTRA");
        patientId = findViewById(R.id.patientId);
        family = findViewById(R.id.family);
        uprogressBar = findViewById(R.id.uprogressBar);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = patientId.getText().toString() + idValue;
                String fa = family.getText().toString();
                if(patientId.getText().toString().isEmpty() || fa.isEmpty()){
                    Toast.makeText(AuthorizePatientActivity.this, "All of input field are required", Toast.LENGTH_SHORT).show();
                }else{
                    String url = "https://boom-test.000webhostapp.com/my-medical/authorize_patient.php?id="+idValue+ "&paitent_id="+id+ "&family="+fa;
                    RequestQueue queue = Volley.newRequestQueue(AuthorizePatientActivity.this);
                    uprogressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    uprogressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(AuthorizePatientActivity.this)
                                            .setTitle("Response")
                                            .setMessage(response)
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Return to the parent activity and reload it
                                                    Intent intent = new Intent(AuthorizePatientActivity.this, PatientChqActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    finish(); // Finish the current activity
                                                }
                                            })
                                            .setCancelable(false)
                                            .show();

                                    patientId.setText("");
                                    family.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            uprogressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);

                }
            }
        });
    }
}