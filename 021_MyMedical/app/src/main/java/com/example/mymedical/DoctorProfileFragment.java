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

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorProfileFragment extends Fragment {
    ProgressBar doctorProgressBar;
    SharedPreferences sharedPreferencesD;
    SharedPreferences.Editor editorD;
    TextView textDoctorID, textName, textDOB, textDegree, textSpecility;
    Button doctorLogoutBtn;
    String doctorId;
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        doctorLogoutBtn = fragmentView.findViewById(R.id.doctorLogoutBtn);
        sharedPreferencesD = getActivity().getSharedPreferences("Loginfileasdoctor", Context.MODE_PRIVATE);
        editorD = sharedPreferencesD.edit();
        doctorLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logout Confirmation")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked "Yes," perform logout
                                editorD.putString("Loginfileasdoctor", "false");
                                editorD.commit();
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

        doctorId = sharedPreferencesD.getString("profileD", "");
        textDoctorID = fragmentView.findViewById(R.id.textDoctorID);
        textName = fragmentView.findViewById(R.id.textName);
        textDOB = fragmentView.findViewById(R.id.textDOB);
        textDegree = fragmentView.findViewById(R.id.textDegree);
        textSpecility = fragmentView.findViewById(R.id.textSpecility);
        doctorProgressBar = fragmentView.findViewById(R.id.doctorProgressBar);
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        doctorProgressBar.setVisibility(View.VISIBLE);
        String url = "https://boom-test.000webhostapp.com/my-medical/doctor_profile.php?doctor_id="+doctorId;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("serverResponse", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String doctorName = jsonObject.getString("name");
                    String doctorDOB = jsonObject.getString("birth");
                    String doctorDegree = jsonObject.getString("degree");
                    String doctorSpeciality = jsonObject.getString("specility");
                    textDoctorID.setText("Doctor ID: " + doctorId);
                    textName.setText("Name: " + doctorName);
                    textDOB.setText("Birth[Y-M-D]: " + doctorDOB);
                    textDegree.setText("Degree: " + doctorDegree);
                    textSpecility.setText("Speciality: " + doctorSpeciality);
                    doctorProgressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    textName.setText("Error! Try Again");
                }
                finally {
                    doctorProgressBar.setVisibility(View.GONE);
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