package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        String pid = getIntent().getStringExtra("passid");
        Spinner spinner = findViewById(R.id.spinner2);
        TextView editText3 = findViewById(R.id.editTextTextPersonName2);
        Button button7 = findViewById(R.id.button7);
        final String[] destination_id = new String[1];


        RequestQueue queue = Volley.newRequestQueue(this);
        String url7 = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid="+pid;
        ArrayList<String> list = new ArrayList<String>();
        StringRequest request1 = new StringRequest(Request.Method.GET, url7, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids = result.split("#");
                for (int i=0;i< ids.length;i++)
                {
                    list.add(ids[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                spinner.setAdapter(adapter);
            }
        },null);
        queue.add(request1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item=parent.getSelectedItem().toString();
                destination_id[0] = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String points = editText3.getText().toString();
                String url8 = "http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+pid+"&dpid="+destination_id[0]+
               "&npoints="+points;
                StringRequest request2 = new StringRequest(Request.Method.GET, url8, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        if (result.contains("SUCCESSFUL"))
                        {
                            Toast.makeText(MainActivity6.this, points+" Points Transferred Successfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity6.this, "Transfer was unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                },null);
                queue.add(request2);
            }
        });

    }
}