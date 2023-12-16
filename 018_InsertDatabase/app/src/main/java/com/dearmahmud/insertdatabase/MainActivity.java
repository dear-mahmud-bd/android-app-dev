package com.dearmahmud.insertdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText ename, eemail, ephone, eaddress;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        ename = findViewById(R.id.name);
        eemail = findViewById(R.id.email);
        ephone = findViewById(R.id.phone);
        eaddress = findViewById(R.id.address);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(myIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                String email = eemail.getText().toString();
                String phone = ephone.getText().toString();
                String address = eaddress.getText().toString();

                if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()){
                    Toast.makeText(MainActivity.this, "All of input field are required", Toast.LENGTH_SHORT).show();
                }else{
                    String url = "https://boom-test.000webhostapp.com/second-app/data.php?name="+name+ "&email="+email+ "&phone="+phone+ "&address="+address ;

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Server Response")
                                            .setMessage(response)
                                            .show();

                                    ename.setText("");
                                    eemail.setText("");
                                    ephone.setText("");
                                    eaddress.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(stringRequest);
                }
            }
        });

    }
}