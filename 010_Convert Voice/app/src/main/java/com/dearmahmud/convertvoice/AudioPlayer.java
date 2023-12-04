package com.dearmahmud.convertvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class AudioPlayer extends AppCompatActivity {
    Button button1, button2, button3;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getTag()!=null && button1.getTag().toString().contains("NOT_PLAYING")){
                    if (mediaPlayer!=null) mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(AudioPlayer.this, R.raw.what_do_you_mean);
                    mediaPlayer.start();
                    button1.setText("Button_File ||");
                    button1.setTag("PLAYING");
                    // when song ends...
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            button1.setText("Button_File |>");
                            button1.setTag("NOT_PLAYING");
                        }
                    });
                }else{
                    if (mediaPlayer!=null) mediaPlayer.release();
                    button1.setText("Button_File |>");
                    button1.setTag("NOT_PLAYING");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getTag()!=null && button2.getTag().toString().contains("NOT_PLAYING")){
                    if (mediaPlayer!=null) mediaPlayer.release();
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource("https://dwn.pagolworld.in/siteuploads/files/sfd3/1404/I'm%20the%20One(Mr-Jatt1.com).mp3");
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        button2.setText("Button_Network ||");
                        button2.setTag("PLAYING");
                        // when audio end...
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                button2.setText("Button_File |>");
                                button2.setTag("NOT_PLAYING");
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    if (mediaPlayer!=null) mediaPlayer.release();
                    button2.setText("Button_Network |>");
                    button2.setTag("NOT_PLAYING");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer!=null) mediaPlayer.release();
                button1.setText("Button_File |>");
                button1.setTag("NOT_PLAYING");
                button2.setText("Button_Network |>");
                button2.setTag("NOT_PLAYING");
            }
        });
    }
}