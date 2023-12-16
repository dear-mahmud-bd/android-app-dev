package com.dearmahmud.insertdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class DatabaseActivity extends AppCompatActivity {
    ListView listView;
    TextView iname, iemail, iphone, iaddress;
    Button loadBtn, buttonUpdate, buttonDelete;
    ProgressBar progressBar;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        loadBtn = findViewById(R.id.loadBtn);
        progressBar = findViewById(R.id.progressBar2);
        listView = findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(DatabaseActivity.this);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                arrayList.clear();

                String url = "https://boom-test.000webhostapp.com/second-app/getdata.php";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                Log.d("serverRes", response);
                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        String personId = jsonObject.getString("id");
                                        String personName = jsonObject.getString("name");
                                        String personEmail = jsonObject.getString("email");
                                        String personPhone = jsonObject.getString("phone");
                                        String personAddress = jsonObject.getString("address");

                                        hashMap = new HashMap<>();
                                        hashMap.put("personId", personId);
                                        hashMap.put("personName", "Name: "+personName);
                                        hashMap.put("personEmail", "Email: "+personEmail);
                                        hashMap.put("personPhone", "Phone: "+personPhone);
                                        hashMap.put("personAddress", "Address: "+personAddress);
                                        arrayList.add(hashMap);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    MyAdapter myAdapter = new MyAdapter();
                                    listView.setAdapter(myAdapter);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadBtn.setText("That didn't work!");
                    }
                });
                queue.add(stringRequest);
            }
        });
    }



    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() { return arrayList.size(); }

        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            // LayoutInflater layoutInflater = getLayoutInflater();
            // View myView = inflater.inflate(R.layout.user_details, null);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = inflater.inflate(R.layout.user_details, viewGroup, false);

            iname = myView.findViewById(R.id.name);
            iemail = myView.findViewById(R.id.email);
            iphone = myView.findViewById(R.id.phone);
            iaddress = myView.findViewById(R.id.address);
            buttonUpdate = myView.findViewById(R.id.buttonUpdate);
            buttonDelete = myView.findViewById(R.id.buttonDelete);

            HashMap<String, String> hashMap = arrayList.get(position);
            String id = hashMap.get("personId");
            String name = hashMap.get("personName");
            String email = hashMap.get("personEmail");
            String phone = hashMap.get("personPhone");
            String address = hashMap.get("personAddress");

            iname.setText(name);
            iemail.setText(email);
            iphone.setText(phone);
            iaddress.setText(address);

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(DatabaseActivity.this, UpdateActivity.class);
                    myIntent.putExtra("ID_EXTRA", id);
                    startActivity(myIntent);
                }
            });

            return myView;
        }
    }
}