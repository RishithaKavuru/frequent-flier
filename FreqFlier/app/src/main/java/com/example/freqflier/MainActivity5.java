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

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        String pid = getIntent().getStringExtra("passid");

        TableLayout tableLayout3 = (TableLayout) findViewById(R.id.TableLayout3);

        TableRow tableRow_head = new TableRow(this);
        TextView tableCol_head0 =new TextView(this);
        TextView tableCol_head1 =new TextView(this);

        tableCol_head0.setText("Redemption Date");
        tableCol_head1.setText("Exchange Center");

        tableCol_head0.setTypeface(Typeface.DEFAULT_BOLD);
        tableCol_head1.setTypeface(Typeface.DEFAULT_BOLD);

        tableCol_head0.setTextSize(18);
        tableCol_head1.setTextSize(18);

        tableRow_head.addView(tableCol_head0);
        tableRow_head.addView(tableCol_head1);

        tableLayout3.addView(tableRow_head);

        Spinner spinner = findViewById(R.id.spinner5);
        TextView textView14 = findViewById(R.id.textView14);
        TextView textView15 = findViewById(R.id.textView15);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url6 = "http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid="+pid;
        ArrayList<String> list = new ArrayList<String>();
        StringRequest request1 = new StringRequest(Request.Method.GET, url6, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids = result.split("#");
                for (int i = 0; i < ids.length; i++) {
                    list.add(ids[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter);
            }
        }, null);
        queue.add(request1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item=parent.getSelectedItem().toString();
                String url5 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+item+"&pid="+pid;
                StringRequest request2 = new StringRequest(Request.Method.GET, url5, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        String[] fd = result.split("#");
                        for (int j=0;j< fd.length;j++)
                        {
                            TableRow tableRow =new TableRow(MainActivity5.this);
                            String row = fd[j];
                            String[] id = row.split(",");
                            textView14.setText(id[0]);
                            textView15.setText(id[1]);
                            TextView col1 = new TextView(MainActivity5.this);
                            TextView col2 = new TextView(MainActivity5.this);
                            col1.setText(id[2]);
                            col2.setText(id[3]);
                            tableRow.addView(col1);
                            tableRow.addView(col2);
                            tableLayout3.addView(tableRow);
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