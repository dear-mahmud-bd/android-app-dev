package com.example.mymedical;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LogInFragment extends Fragment {
    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_log_in, container, false);

        Spinner spinner = fragmentView.findViewById(R.id.spinnerUserLogInType);
        String[] userType = {"User Type", "doctor", "patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, userType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        progressBar = fragmentView.findViewById(R.id.progressLBar);
        editTextEmail = fragmentView.findViewById(R.id.editTextLEmail);
        editTextPassword = fragmentView.findViewById(R.id.editTextLPassword);
        buttonLogin = fragmentView.findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertype = spinner.getSelectedItem().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if (usertype.equals("User Type")) {
                    Toast.makeText(getActivity(), "Select a User type", Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getActivity(), "All input fields are required", Toast.LENGTH_SHORT).show();
                }else if(usertype.equals("patient")){
                    String url = "https://boom-test.000webhostapp.com/my-medical/log_in.php?usertype="+usertype+ "&email="+email+ "&id="+password ;

                    RequestQueue queue = Volley.newRequestQueue(requireContext());

                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Log.d("serverRes", response);
                                    progressBar.setVisibility(View.GONE);
                                    if(response.equals("0")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Enter Email and Password Correctly")
                                                .show();
                                    } else if (response.equals("NULL")) {
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Wait until your account is verified by the Admin")
                                                .show();
                                    } else if(response.equals("Error")){
                                            new AlertDialog.Builder(requireContext())
                                                    .setTitle("Response")
                                                    .setMessage("Something Wrong Try Again Later")
                                                    .show();
                                    }else{
                                        Intent myIntent = new Intent(getActivity(), PatientActivity.class);
                                        myIntent.putExtra("id", response);
                                        startActivity(myIntent);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("serverError", String.valueOf(error));
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                    queue.add(stringRequest);

                }else if(usertype.equals("doctor")){
                    String url = "https://boom-test.000webhostapp.com/my-medical/log_in.php?usertype="+usertype+ "&email="+email+ "&id="+password ;

                    RequestQueue queue = Volley.newRequestQueue(requireContext());

                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Log.d("serverRes", response);
                                    progressBar.setVisibility(View.GONE);
                                    if(response.equals("0")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Enter Email and Password Correctly")
                                                .show();
                                    } else if (response.equals("NULL")) {
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Wait until your account is verified by the Admin")
                                                .show();
                                    } else if(response.equals("Error")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Something Wrong Try Again Later")
                                                .show();
                                    }else{
                                        Intent myIntent = new Intent(getActivity(), DoctorActivity.class);
                                        myIntent.putExtra("id", response);
                                        startActivity(myIntent);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("serverError", String.valueOf(error));
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                    queue.add(stringRequest);

                }
            }
        });

        return fragmentView;
    }
}