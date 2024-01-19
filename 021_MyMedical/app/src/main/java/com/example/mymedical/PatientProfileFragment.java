package com.example.mymedical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class PatientProfileFragment extends Fragment {
    SharedPreferences sharedPreferencesP;
    SharedPreferences.Editor editorP;
    TextView textPatientID, textName, textDOB, textBloodType, textFamilyDisease;
    ProgressBar profileProgressBar;
    Button patientLogoutBtn;
    String patientId;
    public void setPatientId(String patientId) { this.patientId = patientId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_patient_profile, container, false);


        patientLogoutBtn = fragmentView.findViewById(R.id.patientLogoutBtn);
        sharedPreferencesP = getActivity().getSharedPreferences("Loginfileaspatiant", Context.MODE_PRIVATE);
        editorP = sharedPreferencesP.edit();
        patientLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logout Confirmation")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked "Yes," perform logout
                                editorP.putString("Loginfileaspatiant", "false");
                                editorP.commit();
                                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                                startActivity(myIntent);
                                getActivity().finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked "No," stay logged in
                                // Optionally, you can dismiss the dialog here
                            }
                        })
                        .show();
            }
        });
        patientId = sharedPreferencesP.getString("profileP", "");
        textPatientID = fragmentView.findViewById(R.id.textPatientID);
        textName = fragmentView.findViewById(R.id.textName);
        textDOB = fragmentView.findViewById(R.id.textDOB);
        textBloodType = fragmentView.findViewById(R.id.textBloodType);
        textFamilyDisease = fragmentView.findViewById(R.id.textFamilyDisease);
        profileProgressBar = fragmentView.findViewById(R.id.profileProgressBar);


        RequestQueue queue = Volley.newRequestQueue(requireContext());
        profileProgressBar.setVisibility(View.VISIBLE);
        String url = "https://boom-test.000webhostapp.com/my-medical/patient_profile.php?paitent_id="+patientId;
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


                    textPatientID.setText("Patient ID: " + patientId);
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


        return fragmentView;
    }
}
