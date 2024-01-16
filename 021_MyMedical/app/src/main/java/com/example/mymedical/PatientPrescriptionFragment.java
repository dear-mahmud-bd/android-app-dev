package com.example.mymedical;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientPrescriptionFragment extends Fragment {
    ListView prescriptionListView;
    TextView fdoctorName, fdegree, fspeciality, fpatientName, fage, fproblems, fmedicine, ftests, prescriptionErrorMessage;
    ProgressBar patientProgressBar;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();






    String patientId;
    public void setPatientId(String patientId) { this.patientId = patientId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_patient_prescription, container, false);

        prescriptionErrorMessage = fragmentView.findViewById(R.id.prescriptionErrorMessage);
        patientProgressBar = fragmentView.findViewById(R.id.patientProgressBar);
        prescriptionListView = fragmentView.findViewById(R.id.prescriptionListView);

        prescriptionListView.setVisibility(View.GONE);
        arrayList.clear();

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        // RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://boom-test.000webhostapp.com/my-medical/get_prescription.php?paitent_id="+patientId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        patientProgressBar.setVisibility(View.GONE);
                        prescriptionListView.setVisibility(View.VISIBLE);

                        Log.d("serverResponse", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String doctorName = jsonObject.getString("doctor_name");
                                String degree = jsonObject.getString("degree");
                                String speciality = jsonObject.getString("speciality");
                                String patientName = jsonObject.getString("patient_name");
                                String age = jsonObject.getString("age");
                                String problems = jsonObject.getString("problems");
                                String medicine = jsonObject.getString("medicine");
                                String tests = jsonObject.getString("tests");


                                hashMap = new HashMap<>();
                                hashMap.put("doctorName", "Name: "+doctorName);
                                hashMap.put("degree", "Degree: "+degree);
                                hashMap.put("speciality", "Speciality: "+speciality);
                                hashMap.put("patientName", "Name: "+patientName);
                                hashMap.put("age", "Age: "+age);
                                hashMap.put("problems", "Problems: "+problems);
                                hashMap.put("medicine", medicine);
                                hashMap.put("tests", tests);
                                arrayList.add(hashMap);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            MyAdapter myAdapter = new MyAdapter();
                            prescriptionListView.setAdapter(myAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                prescriptionErrorMessage.setText("That didn't work!");
                patientProgressBar.setVisibility(View.GONE);
            }
        });
        queue.add(stringRequest);

        return fragmentView;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            // LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = inflater.inflate(R.layout.prescription, viewGroup, false);

            fdoctorName = myView.findViewById(R.id.doctorname);
            fdegree = myView.findViewById(R.id.doctordegree);
            fspeciality = myView.findViewById(R.id.doctorspeciality);
            fpatientName = myView.findViewById(R.id.patientname);
            fage = myView.findViewById(R.id.patientage);
            fproblems = myView.findViewById(R.id.patientproblem);
            fmedicine = myView.findViewById(R.id.medicine);
            ftests = myView.findViewById(R.id.test);

            HashMap<String, String> hashMap = arrayList.get(position);
            String doctorName = hashMap.get("doctorName");
            String degree = hashMap.get("degree");
            String speciality = hashMap.get("speciality");
            String patientName = hashMap.get("patientName");
            String age = hashMap.get("age");
            String problems = hashMap.get("problems");
            String medicine = hashMap.get("medicine");
            String tests = hashMap.get("tests");

            fdoctorName.setText(doctorName);
            fdegree.setText(degree);
            fspeciality.setText(speciality);
            fpatientName.setText(patientName);
            fage.setText(age);
            fproblems.setText(problems);
            fmedicine.setText(medicine);
            ftests.setText(tests);

            return myView;
        }
    }
}