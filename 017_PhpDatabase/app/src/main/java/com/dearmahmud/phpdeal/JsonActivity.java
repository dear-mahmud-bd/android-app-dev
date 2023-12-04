package com.dearmahmud.phpdeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class JsonActivity extends AppCompatActivity {
    ListView listView;
    TextView iname, iemail, iphone, iaddress;
    Button loadBtn;
    ProgressBar progressBar;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        loadBtn = findViewById(R.id.loadBtn);
        progressBar = findViewById(R.id.progressBar2);

        listView = findViewById(R.id.listView);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                arrayList.clear();
                RequestQueue queue = Volley.newRequestQueue(JsonActivity.this);
                String url = "https://boom-test.000webhostapp.com/first-app/info.json";

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

                                        String personName = jsonObject.getString("name");
                                        String personEmail = jsonObject.getString("email");
                                        String personPhone = jsonObject.getString("phone");
                                        String personAddress = jsonObject.getString("address");

                                        hashMap = new HashMap<>();
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
            View myView = inflater.inflate(R.layout.items, viewGroup, false);

            iname = myView.findViewById(R.id.name);
            iemail = myView.findViewById(R.id.email);
            iphone = myView.findViewById(R.id.phone);
            iaddress = myView.findViewById(R.id.address);

            HashMap<String, String> hashMap = arrayList.get(position);
            String name = hashMap.get("personName");
            String email = hashMap.get("personEmail");
            String phone = hashMap.get("personPhone");
            String address = hashMap.get("personAddress");

            iname.setText(name);
            iemail.setText(email);
            iphone.setText(phone);
            iaddress.setText(address);

            return myView;
        }
    }
}