package com.dearmahmud.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText buy, sell;
    Button calculate;
    TextView display2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buy = findViewById(R.id.buy);
        sell = findViewById(R.id.sell);
        calculate = findViewById(R.id.calculate);
        display2 = findViewById(R.id.display2);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buy.getText().toString().isEmpty() || sell.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity2.this, "Buy and Sell both field are required", Toast.LENGTH_SHORT).show();
                }else{
                    String buyPrice = buy.getText().toString();
                    float by= Float.parseFloat(buyPrice);
                    String sellPrice = sell.getText().toString();
                    float sl= Float.parseFloat(sellPrice);
                    float profit = sl-by;
                    if(profit > 0){
                        display2.setText("Profit: " + profit);
                        display2.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    } else if(profit < 0){
                        display2.setText("Loss: " + -profit);
                        display2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    } else {
                        display2.setText("No Profit with No Loss");
                        display2.setTextColor(getResources().getColor(android.R.color.black));
                    }
                }
            }
        });
    }
}