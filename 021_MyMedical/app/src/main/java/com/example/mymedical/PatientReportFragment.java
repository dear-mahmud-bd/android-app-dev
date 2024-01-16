package com.example.mymedical;

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

public class PatientReportFragment extends Fragment {
    ListView reportListView;
    TextView fpatientName, fage, ftestinfo, ftestresult, reportErrorMessage;
    ProgressBar patientProgressBar;
    HashMap<String, String> rhashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > rarrayList = new ArrayList<>();




    String patientId;
    public void setPatientId(String patientId) { this.patientId = patientId; }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_patient_report, container, false);

        reportErrorMessage = fragmentView.findViewById(R.id.reportErrorMessage);
        patientProgressBar = fragmentView.findViewById(R.id.patientProgressBar);
        reportListView = fragmentView.findViewById(R.id.reportListView);

        reportListView.setVisibility(View.GONE);
        rarrayList.clear();

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        // RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://boom-test.000webhostapp.com/my-medical/get_tests.php?paitent_id="+patientId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                patientProgressBar.setVisibility(View.GONE);
                reportListView.setVisibility(View.VISIBLE);

                Log.d("serverResponse", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String patientName = jsonObject.getString("name");
                        String age = jsonObject.getString("age");
                        String test_info = jsonObject.getString("test_info");
                        String test_result = jsonObject.getString("test_result");


                        rhashMap = new HashMap<>();
                        rhashMap.put("patientName", "Name: "+patientName);
                        rhashMap.put("age", "Age: "+age);
                        rhashMap.put("test_info", test_info);
                        rhashMap.put("test_result", test_result);
                        rarrayList.add(rhashMap);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    PatientReportFragment.ReportAdapter rmyAdapter = new ReportAdapter();
                    reportListView.setAdapter(rmyAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                reportErrorMessage.setText("That didn't work!");
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
    private class ReportAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return rarrayList.size();
        }

        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = inflater.inflate(R.layout.report, viewGroup, false);

            fpatientName = myView.findViewById(R.id.patientname);
            fage = myView.findViewById(R.id.patientage);
            ftestinfo = myView.findViewById(R.id.testinfo);
            ftestresult = myView.findViewById(R.id.testresult);

            HashMap<String, String> rhashMap = rarrayList.get(position);
            String patientName = rhashMap.get("patientName");
            String age = rhashMap.get("age");
            String test_info = rhashMap.get("test_info");
            String test_result = rhashMap.get("test_result");

            fpatientName.setText(patientName);
            fage.setText(age);
            ftestinfo.setText(test_info);
            ftestresult.setText(test_result);

            return myView;
        }
    }
}