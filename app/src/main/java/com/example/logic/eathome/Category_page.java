package com.example.logic.eathome;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Category_page extends AppCompatActivity {
    private static String TAG = Category_page.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<String> addArray = new ArrayList<String>();
    ListView show;
    private String jsonResponse;
    RequestQueue rq;
    public String j;

    //public String[] fooditem = {"NULL"};
    //public String[] price = {"NULL"};


    public String[] val;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        Intent intent = getIntent();
/*
        Bundle bundle = getIntent().getExtras();
        String fi[] = bundle.getStringArray("item");
        String p[] = bundle.getStringArray("price");
        for (int i = 1; i < price.length + 1; i++) {

            fooditem[i] = fi[i];
            price[i] = p[i];
        }
*/
        //Toast.makeText(Category_page.this, "IN FOOD", Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(this);


        show = (ListView) findViewById(R.id.list1);
        sendjsonrequest();

        show.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i,long l){
                //Toast.makeText(Food.this,show.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                String sub = "" + show.getItemAtPosition(i);
                String pass[] = sub.split("\\(");
                sub = pass[0];

                Intent intent = new Intent (Category_page.this,Details.class);
                intent.putExtra("Fid",sub);
                startActivity(intent);
                //Bundle b = new Bundle();
                //b.putString("Fid",sub);
                //b.putStringArray("item", fooditem);
                //b.putStringArray("price", price);
                //intent.putExtras(b);
                //startActivity(intent);
            }
        });

        //btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
        //   @Override

        //       public void onClick(View v) {
    }
    public void sendjsonrequest() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, Constants.PRODUCT_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                                /*Toast.makeText(Food.this, "clicked", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Food.this, response.toString(), Toast.LENGTH_SHORT).show();
                                listResponse.setText("Response: " + response.toString());*/
                try {
                    // Parsing json array response
                    // loop through each json object
                    jsonResponse = "";
                    val = new String[response.length()+1];
                    for (int i = 1; i < response.length()+1; i++) {
                        j = ""+ i+"";
                        JSONObject person = (JSONObject) response.get(j);

                        String fn = person.getString("fn");
                        String cate = person.getString("cat");
                        val[i] =fn + "( " + cate + " )";
                        //jsonResponse += "Food Name: " + fn + "\n\n";
                        //jsonResponse += "Category: " + cate + "\n\n\n";
                        addArray.add(val[i]);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Category_page.this,android.R.layout.simple_list_item_1,addArray);
                        show.setAdapter(adapter);
                    }

                    //listResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        rq.add(jsObjRequest);
    }
}