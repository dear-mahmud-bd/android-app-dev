package com.example.mymedical;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LogInFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_log_in, container, false);

        Spinner spinner = fragmentView.findViewById(R.id.spinnerUserLogInType);
        String[] userType = {"User Type", "Doctor", "Patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, userType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button buttonLogin = fragmentView.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedUserType = spinner.getSelectedItem().toString();
                if (selectedUserType.equals("User Type")) {
                    Toast.makeText(getActivity(), "Select a User type", Toast.LENGTH_SHORT).show();
                }else if(selectedUserType.equals("Patient")){
                    Intent myIntent = new Intent(getActivity(), PatientActivity.class);
                    startActivity(myIntent);
                }else if(selectedUserType.equals("Doctor")){
                    Intent myIntent = new Intent(getActivity(), DoctorActivity.class);
                    startActivity(myIntent);
                }
            }
        });

        return fragmentView;
    }
}