package com.dearmahmud.insertdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
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

public class UpdateActivity extends AppCompatActivity {
    EditText uname, uemail, uphone, uaddress;
    Button ubutton;
    ProgressBar uprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        String idValue = getIntent().getStringExtra("ID_EXTRA");

        uname = findViewById(R.id.uname);
        uemail = findViewById(R.id.uemail);
        uphone = findViewById(R.id.uphone);
        uaddress = findViewById(R.id.uaddress);
        uprogressBar = findViewById(R.id.uprogressBar);
        ubutton = findViewById(R.id.ubutton);


        ubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idValue;
                String name = uname.getText().toString();
                String email = uemail.getText().toString();
                String phone = uphone.getText().toString();
                String address = uaddress.getText().toString();
                if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "All of input field are required", Toast.LENGTH_SHORT).show();
                }else{
                    String url = "https://boom-test.000webhostapp.com/second-app/update.php?id="+id+ "&name="+name+ "&email="+email+ "&phone="+phone+ "&address="+address ;

                    RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);

                    uprogressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    uprogressBar.setVisibility(View.INVISIBLE);
                                    new AlertDialog.Builder(UpdateActivity.this)
                                            .setTitle("Server Response")
                                            .setMessage(response)
                                            .show();

                                    uname.setText("");
                                    uemail.setText("");
                                    uphone.setText("");
                                    uaddress.setText("");
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