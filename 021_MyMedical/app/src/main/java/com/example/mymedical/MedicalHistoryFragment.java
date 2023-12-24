package com.example.mymedical;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MedicalHistoryFragment extends Fragment {

    public static MedicalHistoryFragment newInstance() {
        return new MedicalHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_medical_history, container, false);

        PatientProfileFragment patientProfileFragment = new PatientProfileFragment();
        PatientPrescriptionFragment patientPrescriptionFragment = new PatientPrescriptionFragment();
        PatientReportFragment patientReportFragment = new PatientReportFragment();

//      BottomNavigationView bottomNavigationView = fragmentView.findViewById(R.id.bottomHistoryNavi);

        return fragmentView;
    }
}
