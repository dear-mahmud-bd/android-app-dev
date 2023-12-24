package com.example.mymedical;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DoctorFindPatientFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_doctor_find_patient, container, false);

        EditText editTextPatientSearch = fragmentView.findViewById(R.id.editTextPatientSearch);
        Button buttonPatientSearch = fragmentView.findViewById(R.id.buttonPatientSearch);
        buttonPatientSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), RelationshipActivity.class);
                startActivity(myIntent);
            }
        });

        return fragmentView;
    }
}