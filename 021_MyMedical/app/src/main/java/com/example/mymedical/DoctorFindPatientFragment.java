package com.example.mymedical;

import android.app.AlertDialog;
import android.content.Intent;
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

public class DoctorFindPatientFragment extends Fragment {
    EditText editTextPatientSearch;
    ProgressBar searchProgressBar;
    Button buttonPatientSearch;





    String doctorId;
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_doctor_find_patient, container, false);

        editTextPatientSearch = fragmentView.findViewById(R.id.editTextPatientSearch);
        buttonPatientSearch = fragmentView.findViewById(R.id.buttonPatientSearch);
        searchProgressBar = fragmentView.findViewById(R.id.searchProgressBar);
        buttonPatientSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paitentId = editTextPatientSearch.getText().toString();
                 if(paitentId.isEmpty()){
                     Toast.makeText(getActivity(), "Valid patient ID required", Toast.LENGTH_SHORT).show();
                 }else{
                     String url = "https://boom-test.000webhostapp.com/my-medical/find_patient.php?paitent_id="+paitentId ;

                     RequestQueue queue = Volley.newRequestQueue(requireContext());

                     searchProgressBar.setVisibility(View.VISIBLE);
                     StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                             new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {
                                     // Log.d("serverRes", response);
                                     searchProgressBar.setVisibility(View.GONE);
                                     if (response.equals("0")){
                                         new AlertDialog.Builder(requireContext())
                                                 .setTitle("Response")
                                                 .setMessage("There is no Patient against this Patient ID")
                                                 .show();
                                     } else if (response.equals("NULL")) {
                                         new AlertDialog.Builder(requireContext())
                                                 .setTitle("Response")
                                                 .setMessage("Patient is not Verified")
                                                 .show();
                                     } else if(response.equals("Error")){
                                         new AlertDialog.Builder(requireContext())
                                                 .setTitle("Response")
                                                 .setMessage("Something Wrong Try Again Later")
                                                 .show();
                                     }else{
                                         Intent myIntent = new Intent(getActivity(), RelationshipActivity.class);
                                         myIntent.putExtra("doctorId", doctorId);
                                         myIntent.putExtra("paitentId", paitentId);
                                         startActivity(myIntent);
                                     }
                                 }
                             }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Log.d("serverError", String.valueOf(error));
                             // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                             Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                             searchProgressBar.setVisibility(View.GONE);
                         }
                     });
                     queue.add(stringRequest);
                 }
            }
        });

        return fragmentView;
    }
}