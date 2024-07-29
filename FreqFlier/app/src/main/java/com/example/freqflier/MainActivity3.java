package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity3 extends AppCompatActivity {
    private TableLayout mTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        String pid = getIntent().getStringExtra("passid");
        TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout);

        TableRow tableRow_head = new TableRow(this);
        TextView tableCol_head0 =new TextView(this);
        TextView tableCol_head1 =new TextView(this);
        TextView tableCol_head2 =new TextView(this);

        tableCol_head0.setText("Flight Id");
        tableCol_head1.setText("Flight Miles");
        tableCol_head2.setText("Destination");

        tableCol_head0.setTypeface(Typeface.DEFAULT_BOLD);
        tableCol_head1.setTypeface(Typeface.DEFAULT_BOLD);
        tableCol_head2.setTypeface(Typeface.DEFAULT_BOLD);

        tableCol_head0.setTextSize(18);
        tableCol_head1.setTextSize(18);
        tableCol_head2.setTextSize(18);

        tableRow_head.addView(tableCol_head0);
        tableRow_head.addView(tableCol_head1);
        tableRow_head.addView(tableCol_head2);
        tableLayout.addView(tableRow_head);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url3 = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        StringRequest request_info = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                    String result = s.trim();
                    String[] rows = result.split("#");
                    for (int i = 0; i < rows.length; i++) {

                        TableRow tableRow = new TableRow(MainActivity3.this);
                        String row = rows[i];
                        String[] col = row.split(",");
                        TextView col1 = new TextView(MainActivity3.this);
                        TextView col2 = new TextView(MainActivity3.this);
                        TextView col3 = new TextView(MainActivity3.this);
                        col1.setText(col[0]);
                        col2.setText(col[1]);
                        col3.setText(col[2]);
                        tableRow.addView(col1);
                        tableRow.addView(col2);
                        tableRow.addView(col3);
                        tableLayout.addView(tableRow);

                    }
            }
        },null);
        queue.add(request_info);
    }

}