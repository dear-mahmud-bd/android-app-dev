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

public class AddReportFragment extends Fragment {
    EditText testName, testResult;
    ProgressBar reportProgressBar;
    Button reportButton;




    String patientId;
    public void setPatientId(String patientId) { this.patientId = patientId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_add_report, container, false);

        testName = fragmentView.findViewById(R.id.testName);
        testResult = fragmentView.findViewById(R.id.testResult);
        reportProgressBar = fragmentView.findViewById(R.id.reportProgressBar);
        reportButton = fragmentView.findViewById(R.id.reportButton);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tName = testName.getText().toString();
                String tResult = testResult.getText().toString();

                if(tName.isEmpty() || tResult.isEmpty()){
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Response")
                            .setMessage("All input field are required")
                            .show();
                } else {
                    RequestQueue queue = Volley.newRequestQueue(requireContext());
                    String url = "https://boom-test.000webhostapp.com/my-medical/add_report.php?paitent_id="+patientId+ "&test_info="+tName+ "&test_result="+tResult;
                    reportProgressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Log.d("serverRes", response);
                                    reportProgressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(requireContext())
                                            .setTitle("Response")
                                            .setMessage(response)
                                            .show();

                                    testName.setText("");
                                    testResult.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("serverError", String.valueOf(error));
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "That didn't work! ", Toast.LENGTH_LONG).show();
                            reportProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                    queue.add(stringRequest);


                }
            }
        });

        return fragmentView;
    }
}