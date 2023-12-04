package com.dearmahmud.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);

        createDB();

        MyAdapter adapter = new MyAdapter();
        gridView.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item_layout, parent, false);

            TextView textView = myView.findViewById(R.id.textView);
            ImageView imgView = myView.findViewById(R.id.imgView);
            HashMap<String, String> hashMap = arrayList.get(position);

            String name = hashMap.get("name");
            String imageName = hashMap.get("img");

            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), resID);

                textView.setText(name);
                imgView.setImageBitmap(imageBitmap);



            return myView;
        }
    }



    private void createDB(){
        hashMap = new HashMap<>();
        hashMap.put("name", "Bag Pack");
        hashMap.put("img", "bag.png");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Bag Pack");
        hashMap.put("img", "shop.png");
        arrayList.add(hashMap);
    }
}