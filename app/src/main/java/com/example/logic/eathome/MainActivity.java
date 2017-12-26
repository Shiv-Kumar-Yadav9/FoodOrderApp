package com.example.logic.eathome;

import android.app.DownloadManager;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//Internet in manifest in manifest in app in android view
//add volley library in the build gradle in graddle scripts in android

    TextView uname, eml, pswd;
    Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (TextView) findViewById(R.id.user);
        eml = (TextView) findViewById(R.id.mail);
        pswd = (TextView) findViewById(R.id.pwd);
        signup = (Button) findViewById(R.id.sup);
        login = (Button) findViewById(R.id.lin);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String name = uname.getText().toString();

                final String email = eml.getText().toString();
                final String password = pswd.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //Toast.makeText(MainActivity.this, "BUTTON CLICKED", Toast.LENGTH_SHORT).show();
                                if (response.contains("Please check your email!!!") || response.contains(" E-mail already exists!!!")) {
                                    /*final FragmentManager fragmentManager = getApplicationContext().getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.content,new LoginFragment()).commit();
                                     */

                                    Intent intent = new Intent(getApplicationContext(),Login.class);
                                    startActivity(intent);
                                }
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            Toast.makeText(MainActivity.this, "Time out error!!!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(MainActivity.this, "No connection error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(MainActivity.this, "Auth failure error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(MainActivity.this, "Parse error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(Constants.KEY_NAME, name);
                        params.put(Constants.KEY_EMAIL, email);
                        params.put(Constants.KEY_PASSWORD, password);
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("User-Agent", "EatIt");
                        return headers;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
    }
}
