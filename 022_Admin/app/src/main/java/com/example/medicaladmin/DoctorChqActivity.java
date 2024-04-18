package com.example.medicaladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class DoctorChqActivity extends AppCompatActivity {
    ProgressBar dProgressBar;
    ListView dListView;
    TextView dAuthorize, iname, iemail, ibirth, iblood;
    Button buttonDelete, buttonAuthorize;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_chq);

        dProgressBar = findViewById(R.id.dProgressBar);
        dListView = findViewById(R.id.dListView);
        dAuthorize = findViewById(R.id.dAuthorize);

        dProgressBar.setVisibility(View.VISIBLE);
        dListView.setVisibility(View.GONE);
        arrayList.clear();

        RequestQueue queue = Volley.newRequestQueue(DoctorChqActivity.this);
        String url = "https://boom-test.000webhostapp.com/my-medical/chq_doctor.php";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dProgressBar.setVisibility(View.GONE);
                        dListView.setVisibility(View.VISIBLE);
                        Log.d("serverRes", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String personId = jsonObject.getString("id");
                                String personName = jsonObject.getString("name");
                                String personEmail = jsonObject.getString("email_id");
                                String personBirth = jsonObject.getString("birth");
                                String personBlood = jsonObject.getString("blood");


                                hashMap = new HashMap<>();
                                hashMap.put("personId", personId);
                                hashMap.put("personName", "Name: "+personName);
                                hashMap.put("personEmail", "Email: "+personEmail);
                                hashMap.put("personBirth", "Birth: "+personBirth);
                                hashMap.put("personBlood", "Blood: "+personBlood);
                                arrayList.add(hashMap);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            MyAdapter myAdapter = new MyAdapter();
                            dListView.setAdapter(myAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dAuthorize.setText("That didn't work! Try Again");
            }
        });
        queue.add(stringRequest);
    }

    private class MyAdapter extends BaseAdapter {
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
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = inflater.inflate(R.layout.user, viewGroup, false);

            iname = myView.findViewById(R.id.name);
            iemail = myView.findViewById(R.id.email);
            ibirth = myView.findViewById(R.id.birth);
            iblood = myView.findViewById(R.id.blood);

            buttonDelete = myView.findViewById(R.id.buttonDelete);
            buttonAuthorize = myView.findViewById(R.id.buttonAuthorize);

            HashMap<String, String> hashMap = arrayList.get(position);
            String id = hashMap.get("personId");
            String name = hashMap.get("personName");
            String email = hashMap.get("personEmail");
            String birth = hashMap.get("personBirth");
            String blood = hashMap.get("personBlood");

            buttonAuthorize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(DoctorChqActivity.this, AuthorizeDoctorActivity.class);
                    myIntent.putExtra("ID_EXTRA", id);
                    startActivity(myIntent);
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show an alert dialog with Yes and No buttons
                    new AlertDialog.Builder(DoctorChqActivity.this)
                            .setTitle("Confirm Deletion")
                            .setMessage("Are you sure you want to delete?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User clicked Yes button
                                    executeDeletion(id, "doctor"); // Function to make network request and handle response
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            iname.setText(name);
            iemail.setText(email);
            ibirth.setText(birth);
            iblood.setText(blood);

            return myView;
        }
    }


    private void executeDeletion(String id, String table) {
        String url = "https://boom-test.000webhostapp.com/my-medical/delete_fake_account.php?id="+id + "&table_name="+table;
        RequestQueue queue = Volley.newRequestQueue(DoctorChqActivity.this);
        dProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dProgressBar.setVisibility(View.INVISIBLE);
                        new AlertDialog.Builder(DoctorChqActivity.this)
                                .setTitle("Response")
                                .setMessage(response)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Reload the activity
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                dProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }



}