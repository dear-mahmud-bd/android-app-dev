package com.example.mymedical;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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


public class SignUpFragment extends Fragment {
    ProgressBar progressBar;
    EditText editTextName, editTextBlood, emailEditText, passwordEditText, confirmPasswordEditText;
    TextView textViewDOB;
    Button buttonSignUp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Spinner spinner = fragmentView.findViewById(R.id.spinnerUserType);
        String[] userType = {"User Type", "doctor", "patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, userType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        progressBar = fragmentView.findViewById(R.id.progressBar);
        editTextName = fragmentView.findViewById(R.id.editTextName);
        textViewDOB = fragmentView.findViewById(R.id.textViewDOB);
        editTextBlood = fragmentView.findViewById(R.id.editTextBlood);
        emailEditText = fragmentView.findViewById(R.id.editTextEmail);
        passwordEditText = fragmentView.findViewById(R.id.editTextPassword);
        confirmPasswordEditText = fragmentView.findViewById(R.id.editTextConfirmPassword);
        buttonSignUp = fragmentView.findViewById(R.id.buttonSignUp);

        textViewDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertype = spinner.getSelectedItem().toString();
                String name = editTextName.getText().toString();
                String dobText = textViewDOB.getText().toString();
                String blood = editTextBlood.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (dobText == null || dobText.isEmpty()) {
                    Toast.makeText(getActivity(), "Set yor Birth", Toast.LENGTH_SHORT).show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Valid Email is required");
                    emailEditText.requestFocus();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty() || password.isEmpty() || name.isEmpty() || blood.isEmpty()) {
                    Toast.makeText(getActivity(), "All input fields are required", Toast.LENGTH_SHORT).show();
                } else if (usertype.equals("User Type")) {
                    Toast.makeText(getActivity(), "Select a User type", Toast.LENGTH_SHORT).show();
                } else {
                    RequestQueue queue = Volley.newRequestQueue(requireContext());
                    String chq = "https://boom-test.000webhostapp.com/my-medical/unique_email.php?email="+email+ "&table_name="+usertype;
                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringReq = new StringRequest(Request.Method.GET, chq,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String res) {
                                    // Display the first 500 characters of the response string.
                                    if(res.equals("yes")){
                                        Toast.makeText(requireContext(), "This mail already taken", Toast.LENGTH_LONG).show();
                                    }else{
                                        String url = "https://boom-test.000webhostapp.com/my-medical/sign_up.php?usertype="+usertype+ "&name="+name+ "&dob="+dobText+ "&blood="+blood+ "&email="+email+ "&id="+password ;
                                        progressBar.setVisibility(View.VISIBLE);
                                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        // Log.d("serverRes", response);
                                                        progressBar.setVisibility(View.GONE);
                                                        new AlertDialog.Builder(requireContext())
                                                                .setTitle("Registration Successful !")
                                                                .setMessage(response)
                                                                .setCancelable(false)
                                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.dismiss(); // The user clicked "Yes", Dismiss the dialog
                                                                    }
                                                                })
                                                                .show();

                                                        editTextName.setText("");
                                                        editTextBlood.setText("");
                                                        emailEditText.setText("");
                                                        passwordEditText.setText("");
                                                        confirmPasswordEditText.setText("");
                                                        // textViewDOB.setHint("Date Of Birth [Year-Month-Day]");
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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog( requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = year + "-" + (month + 1) + "-" + day;
                        textViewDOB.setText(selectedDate);
                    }
                },
                2005, 0, 1 // Set the default date in the dialog (optional)
        );
        datePickerDialog.show(); // Show the date picker dialog
    }
}