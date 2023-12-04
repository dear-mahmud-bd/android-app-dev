package com.dearmahmud.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList< HashMap<String,String> > arrayList = new ArrayList();
    HashMap <String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        hashMap = new HashMap<>();
        hashMap.put("name","Ami Topu");
        hashMap.put("phone","012345678910");
        hashMap.put("mail","ami@topu.net");
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("name","Ami Mahmudul");
        hashMap.put("phone","012345678911");
        hashMap.put("mail","ami@mahmudul.onk");
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("name","Ami Mahmud");
        hashMap.put("phone","012345678912");
        hashMap.put("mail","ami@mahmud.micro");
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("name","Amr Bou Secret");
        hashMap.put("phone","************");
        hashMap.put("mail","amr@bou.mail");
        arrayList.add(hashMap);


        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    
    private class MyAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;

        @Override
        public int getCount() {
            return  arrayList.size();
            // return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item_layout, viewGroup,false);

            ImageView imageView = myView.findViewById(R.id.imageView);
            TextView textView = myView.findViewById(R.id.textView);
            TextView textView2 = myView.findViewById(R.id.textView2);
            TextView textView3 = myView.findViewById(R.id.textView3);

            HashMap<String, String> hashMap1 = arrayList.get(position);
            String name = hashMap1.get("name");
            String phone = hashMap1.get("phone");
            String mail = hashMap1.get("mail");

            textView.setText(name);
            textView2.setText(phone);
            textView3.setText(mail);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "ImageView Clicked! "+(position+1), Toast.LENGTH_SHORT).show();
                }
            });

            return myView;
        }
    }
}