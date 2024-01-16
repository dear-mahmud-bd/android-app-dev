package com.example.mymedical;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AddPrescriptionFragment extends Fragment {
    ProgressBar addPrescriptionProgressBar;
    EditText editTextPatientProblem, editTextMedicalTest, editTextTreatmentMedicine;
    Button addPrescription;




    String patientId, doctorId;
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_add_prescription, container, false);

        addPrescriptionProgressBar = fragmentView.findViewById(R.id.addPrescriptionProgressBar);
        editTextPatientProblem = fragmentView.findViewById(R.id.editTextPatientProblem);
        editTextMedicalTest = fragmentView.findViewById(R.id.editTextMedicalTest);
        editTextTreatmentMedicine = fragmentView.findViewById(R.id.editTextTreatmentMedicine);
        addPrescription = fragmentView.findViewById(R.id.addPrescription);

        addPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problem = editTextPatientProblem.getText().toString();
                String test = editTextMedicalTest.getText().toString();
                String medicine = editTextTreatmentMedicine.getText().toString();

                if(problem.isEmpty() || medicine.isEmpty()){
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Response")
                            .setMessage("Patient Problem and Medicine are Required")
                            .show();
                }else{
                    if(test.isEmpty()) test = "NONE";
                    RequestQueue queue = Volley.newRequestQueue(requireContext());
                    String url = "https://boom-test.000webhostapp.com/my-medical/add_prescription.php?doctor_id="+doctorId+ "&paitent_id="+patientId+ "&problem="+problem+ "&test="+test+ "&medicine="+medicine;

                    addPrescriptionProgressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Log.d("serverRes", response);
                                    addPrescriptionProgressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(requireContext())
                                            .setTitle("Response")
                                            .setMessage(response)
                                            .show();

                                    editTextPatientProblem.setText("");
                                    editTextMedicalTest.setText("");
                                    editTextTreatmentMedicine.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("serverError", String.valueOf(error));
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                            addPrescriptionProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                    queue.add(stringRequest);
                }

            }
        });

        return fragmentView;
    }
}