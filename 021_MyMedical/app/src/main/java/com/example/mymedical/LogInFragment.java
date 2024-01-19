package com.example.mymedical;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
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
    SharedPreferences sharedPreferencesP,sharedPreferencesD;
    SharedPreferences.Editor editorP,editorD;
    EditText editTextEmail, editTextPassword;
    String Id;
    TextView textView;
    Button buttonLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_log_in, container, false);

        sharedPreferencesD = getActivity().getSharedPreferences("Loginfileasdoctor", Context.MODE_PRIVATE);
        editorD = sharedPreferencesD.edit();
        if(sharedPreferencesD.getString("Loginfileasdoctor","").equals("true")){
            Intent myIntent = new Intent(getActivity(), DoctorActivity.class);
            startActivity(myIntent);
            getActivity().finishAffinity();
        }
        sharedPreferencesP = getActivity().getSharedPreferences("Loginfileaspatiant", Context.MODE_PRIVATE);
        editorP = sharedPreferencesP.edit();
        if(sharedPreferencesP.getString("Loginfileaspatiant","").equals("true")){
            Intent myIntent = new Intent(getActivity(), PatientActivity.class);
            startActivity(myIntent);
            getActivity().finishAffinity();
        }

        Spinner spinner = fragmentView.findViewById(R.id.spinnerUserLogInType);
        String[] userType = {"User Type", "doctor", "patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, userType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        progressBar = fragmentView.findViewById(R.id.progressLBar);
        editTextEmail = fragmentView.findViewById(R.id.editTextLEmail);
        editTextPassword = fragmentView.findViewById(R.id.editTextLPassword);
        textView = fragmentView.findViewById(R.id.textView);
        buttonLogin = fragmentView.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertype = spinner.getSelectedItem().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if (usertype.equals("User Type")) {
                    Toast.makeText(getActivity(), "Select a User type", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Valid Email is required");
                    editTextEmail.requestFocus();
                } else if(email.isEmpty() || password.isEmpty()){
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
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss(); // The user clicked "Yes", Dismiss the dialog
                                                    }
                                                })
                                                .show();
                                    } else if(response.equals("Error")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Something Wrong Try Again Later")
                                                .show();
                                    }else{
                                        //Id = String.valueOf(response);
                                        String profileP = response;
                                        editorP.putString("profileP",profileP);
                                        editorP.putString("Loginfileaspatiant","true");
                                        editorP.commit();
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
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss(); // The user clicked "Yes", Dismiss the dialog
                                                    }
                                                })
                                                .show();
                                    } else if(response.equals("Error")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Response")
                                                .setMessage("Something Wrong Try Again Later")
                                                .show();
                                    }else{
                                        String profileD = response;
                                        editorD.putString("profileD",profileD);
                                        editorD.putString("Loginfileasdoctor","true");
                                        editorD.commit();
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

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertype = spinner.getSelectedItem().toString();
                String email = editTextEmail.getText().toString();
                if(usertype.equals("User Type")){
                    Toast.makeText(getActivity(), "Select a user type", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
                    editTextEmail.setError("Valid Email is required");
                    editTextEmail.requestFocus();
                } else {
                    RequestQueue queue = Volley.newRequestQueue(requireContext());
                    String chq = "https://boom-test.000webhostapp.com/my-medical/unique_email.php?email="+email+ "&table_name="+usertype;
                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringReq = new StringRequest(Request.Method.GET, chq,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String res) {
                                    // Display the first 500 characters of the response string.
                                    if(res.equals("no")){
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Wrong email !")
                                                .setMessage("No account has been created for this email")
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss(); // The user clicked "Yes", Dismiss the dialog
                                                    }
                                                })
                                                .show();
                                    }else{
                                        new AlertDialog.Builder(requireContext())
                                                .setTitle("Send you a mail!")
                                                .setMessage("Check your mail and follow the instructions to recover your password or change your password")
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss(); // The user clicked "Yes", Dismiss the dialog
                                                    }
                                                })
                                                .show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError err) {
                            Log.d("serverError", String.valueOf(err));
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                    queue.add(stringReq);

                }
            }
        });

        return fragmentView;
    }

}
