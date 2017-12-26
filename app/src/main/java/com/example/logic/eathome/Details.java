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

public class Details extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    private static String TAG = Category_page.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<String> addArray = new ArrayList<String>();
    ListView show;
    private String jsonResponse;
    RequestQueue rq;
    public String j;
    public String fname;

    public static final int MY_SOCKET_TIMEOUT_MS = 30000;

    //JSONObject json = new JSONObject();
    public String[] val;

    //public String[] fooditem = {"NULL"};
    //public String[] price = {"NULL"};
    int f=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDatabaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        //String fi[] = bundle.getStringArray("item");
        //String p[] = bundle.getStringArray("price");
        fname = bundle.getString("Fid");

        fname = Constants.DB_URL + fname + ".php";
        //Toast.makeText(Category_page.this, "IN FOOD", Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(this);
        /*
        for (int i = 1; i < price.length + 1; i++) {

            fooditem[i] = fi[i];
            price[i] = p[i];
        }
*/

        Button cart;


        show = (ListView) findViewById(R.id.list1);
        sendjsonrequest();

        show.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Toast.makeText(Food.this,show.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                String sub = "" + show.getItemAtPosition(i);
                String pass[] = sub.split("\\(");
                //int p = price.length;
                String newEntry = pass[0];

                String pa[] = sub.split("\\n");
                int n2 = Integer.parseInt(pa[1]);
                //Toast.makeText(Details.this,Integer.toString(n2),Toast.LENGTH_SHORT).show();
                boolean insertData = mDatabaseHelper.addData(newEntry,n2);

                if (insertData){
                    Toast.makeText(Details.this, "Item placed in cart.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Details.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });
        cart = (Button) findViewById(R.id.b1);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Cart.class);
                startActivity(intent);
                /*Bundle b = new Bundle();
                b.putStringArray("item", fooditem);
                b.putStringArray("price", price);
                Intent j = new Intent(Details.this, Cart.class);
                j.putExtras(b);
                startActivity(j);
                */
            }
        });
    }
    public void sendjsonrequest() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,fname, null, new Response.Listener<JSONObject>() {

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

                        String fn = person.getString("name");
                        String cate = person.getString("shop");
                        String cst = person.getString("cost");
                        val[i] =fn + "( " + cate + " )\n" + cst;
                        //jsonResponse += "Food Name: " + fn + "\n\n";
                        //jsonResponse += "Category: " + cate + "\n\n\n";
                        addArray.add(val[i]);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Details.this,android.R.layout.simple_list_item_1,addArray);
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