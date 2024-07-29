package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String pid = getIntent().getStringExtra("passid");
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        ImageView imageView = findViewById(R.id.imageView);
        Button button_fd = findViewById(R.id.button2);
        Button button_af = findViewById(R.id.button3);
        Button button_ri = findViewById(R.id.button4);
        Button button_tp = findViewById(R.id.button5);
        Button button_exit = findViewById(R.id.button6);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url1 = "http://10.0.2.2:8080/frequentflier/Info.jsp?pid="+pid;
        StringRequest request_info = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] results = result.split(",");
                textView4.setText(results[0]);
                textView5.setText(results[1]);
            }
        },null);
        queue.add(request_info);

        String url2 = "http://10.0.2.2:8080/frequentflier/images/"+pid+".jpeg";
        ImageRequest request_img = new ImageRequest(url2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);

            }
        },0,0,null,null);
        queue.add(request_img);

        button_fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

        button_af.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });


        button_ri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });


        button_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity6.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

    }
}