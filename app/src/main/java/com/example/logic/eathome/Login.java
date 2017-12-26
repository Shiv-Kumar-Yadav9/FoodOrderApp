package com.example.logic.eathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
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


public class Login extends AppCompatActivity {

    EditText un;
    EditText p;
    Button li;
    //public String[] fooditem = {"NULL"};
    //public String[] price = {"NULL"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();

        un =(EditText) findViewById(R.id.untext);
        p = (EditText) findViewById(R.id.ptext);
        li = (Button) findViewById(R.id.lbutton);

        li.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String email = un.getText().toString();
                final String password = p.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (response.contains("Confirmed")) {
                                    Intent intent = new Intent(getApplicationContext(),Category_page.class);
                                    startActivity(intent);
                                    /*Bundle b = new Bundle();

                                    b.putStringArray("item", fooditem);
                                    b.putStringArray("price", price);
                                    Intent j = new Intent(Login.this, Category_page.class);
                                    j.putExtras(b);
                                    startActivity(j);
                                    */
                                }
                                else if (response.contains("Sign up First"))
                                {
                                    /*final FragmentManager fragmentManager = getApplicationContext().getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.content,new LoginFragment()).commit();
                                     */
                                    //Toast.makeText(MainActivity.this, "BUTTON CLICKED", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                }

                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            Toast.makeText(Login.this, "Time out error!!!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(Login.this, "No connection error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(Login.this, "Auth failure error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(Login.this, "Network error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(Login.this, "Server error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(Login.this, "Parse error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
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
