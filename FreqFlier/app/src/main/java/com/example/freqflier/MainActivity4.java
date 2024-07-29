package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String pid = getIntent().getStringExtra("passid");

        TableLayout tableLayout2 = (TableLayout) findViewById(R.id.TableLayout2);

        TableRow tableRow_head = new TableRow(this);
        TextView tableCol_head0 =new TextView(this);
        TextView tableCol_head1 =new TextView(this);

        tableCol_head0.setText("Trip Id");
        tableCol_head1.setText("Trip Miles");

        tableCol_head0.setTypeface(Typeface.DEFAULT_BOLD);
        tableCol_head1.setTypeface(Typeface.DEFAULT_BOLD);

        tableCol_head0.setTextSize(18);
        tableCol_head1.setTextSize(18);

        tableRow_head.addView(tableCol_head0);
        tableRow_head.addView(tableCol_head1);

        tableLayout2.addView(tableRow_head);


        Spinner spinner = findViewById(R.id.spinner4);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView12 = findViewById(R.id.textView12);
        TextView textView13 = findViewById(R.id.textView13);
        TextView editText3 = findViewById(R.id.editTextTextPersonName3);
        TextView editText4 = findViewById(R.id.editTextTextPersonName4);
        TextView editText5 = findViewById(R.id.editTextTextPersonName5);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url4 = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        ArrayList<String> list = new ArrayList<String>();
        StringRequest request1 = new StringRequest(Request.Method.GET, url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] fls = result.split("#");
                for (int j=0;j< fls.length;j++)
                {
                    String row = fls[j];
                    String[] id = row.split(",");
                    list.add(id[0]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                spinner.setAdapter(adapter);
            }
        },null);
        queue.add(request1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item=parent.getSelectedItem().toString();
                String url5 = "http://10.0.2.2:8080/frequentflier/FlightDetails.jsp?flightid="+item;
                StringRequest request2 = new StringRequest(Request.Method.GET, url5, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        String[] fd = result.split("#");
                        for (int j=0;j< fd.length;j++)
                        {
                            TableRow tableRow =new TableRow(MainActivity4.this);
                            String row = fd[j];
                            String[] id = row.split(",");
                            editText3.setText(id[0]);
                            editText4.setText(id[1]);
                            editText5.setText(id[2]);
                            TextView col1 = new TextView(MainActivity4.this);
                            TextView col2 = new TextView(MainActivity4.this);
                            col1.setText(id[3]);
                            col2.setText(id[4]);
                            tableRow.addView(col1);
                            tableRow.addView(col2);
                            tableLayout2.addView(tableRow);
                        }

                    }
                },null);
                queue.add(request2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}