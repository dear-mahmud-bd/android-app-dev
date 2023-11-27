package com.dearmahmud.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewDisplay, numberConversion;
    StringBuilder currentNumber;
    double num1, num2;
    String operator;
    Button btnSeven,btnEight,btnNine,btnDivide,btnFour,btnFive,btnSix,btnMulti,btnOne,btnTwo,btnThree,btnMinu,btnEqual,btnPls,btnBinary,btnOctal,btnHexa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);
        numberConversion = findViewById(R.id.numberConvertion);
        currentNumber = new StringBuilder();

        btnSeven = findViewById(R.id.seven);
        btnEight = findViewById(R.id.eight);
        btnNine = findViewById(R.id.nine);
        btnDivide = findViewById(R.id.div);
        btnFour = findViewById(R.id.four);
        btnFive = findViewById(R.id.five);
        btnSix = findViewById(R.id.six);
        btnMulti = findViewById(R.id.mul);
        btnOne = findViewById(R.id.one);
        btnTwo = findViewById(R.id.two);
        btnThree = findViewById(R.id.three);
        btnMinu = findViewById(R.id.minu);
        btnEqual = findViewById(R.id.equal);
        btnPls = findViewById(R.id.pls);
        btnBinary = findViewById(R.id.binary);
        btnOctal = findViewById(R.id.octal);
        btnHexa = findViewById(R.id.hexa);
    }
}