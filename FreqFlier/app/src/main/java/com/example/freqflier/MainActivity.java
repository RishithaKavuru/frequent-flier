package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editTextTextPersonName);
        EditText editText2 = findViewById(R.id.editTextTextPassword);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String user = editText.getText().toString();
                String pass = editText2.getText().toString();
                String url = "http://10.0.2.2:8080/frequentflier/login?user="+user+"&pass="+pass;
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        if (result.contains("Yes")) {
                            //Toast.makeText(MainActivity.this, "Valid Credintials", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            String[] auth = result.split(":");
                            String pid = auth[1];
                            intent.putExtra("passid",pid);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "INCORRECT Username/Password Credintials", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                queue.add(request);

            }
        });
    }
}