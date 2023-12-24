package com.example.mymedical;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Spinner spinner = fragmentView.findViewById(R.id.spinnerUserType);
        String[] userType = {"User Type", "Doctor", "Patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, userType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText editTextName = fragmentView.findViewById(R.id.editTextName);
        EditText emailEditDOB = fragmentView.findViewById(R.id.editTextDOB);
        EditText editTextBlood = fragmentView.findViewById(R.id.editTextBlood);
        EditText emailEditText = fragmentView.findViewById(R.id.editTextEmail);
        EditText passwordEditText = fragmentView.findViewById(R.id.editTextPassword);
        EditText confirmPasswordEditText = fragmentView.findViewById(R.id.editTextConfirmPassword);
        Button buttonSignUp = fragmentView.findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedUserType = spinner.getSelectedItem().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "All input fields are required", Toast.LENGTH_SHORT).show();
                } else if (selectedUserType.equals("User Type")) {
                    Toast.makeText(getActivity(), "Select a User type", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "A " + selectedUserType + " account has been created\nPlease Log In...", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new LogInFragment());
                    fragmentTransaction.commit();
                }
            }
        });

        return fragmentView;
    }
}