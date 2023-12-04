package com.dearmahmud.convertvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edText;
    Button btnVoice,btnAlert, exit, net, audioPlayer, pdf;
    TextToSpeech voice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edText = findViewById(R.id.edText);
        btnVoice = findViewById(R.id.btnVoice);
        btnAlert = findViewById(R.id.btnAlert);
        net = findViewById(R.id.net);
        exit = findViewById(R.id.exit);
        audioPlayer = findViewById(R.id.audioPlayer);
        pdf = findViewById(R.id.pdf);

        voice = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edText.getText().toString().isEmpty()){
                    voice.speak("Abbe sallee kuch to lekhh", TextToSpeech.QUEUE_FLUSH, null, null);
                }else{
                    voice.speak(""+edText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is TITLE")
                        .setMessage("Hello, Apni hudai alert message dekte kn chan? Nijeke ask korun...")
                        .setCancelable(false)
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Dialog Closed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){
                    Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Network Unavailable", Toast.LENGTH_SHORT).show();
                }
            }
        });

        audioPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AudioPlayer.class);
                startActivity(intent);
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PdfActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm Exit!!!")
                .setMessage("Do you really want to exit?")
                .setIcon(R.drawable.alert)
                .setNegativeButton("No, thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAndRemoveTask();
                    }
                })
                .show();
    }
}