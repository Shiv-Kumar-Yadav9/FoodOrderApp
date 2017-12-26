package com.example.logic.eathome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private static String TAG = Category_page.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<String> addArray = new ArrayList<String>();
    ListView show;

    public static final int MY_SOCKET_TIMEOUT_MS = 30000;

    //JSONObject json = new JSONObject();
    public String[] val;
    int k=0;
    //public String[] fooditem = {"NULL"};
    //public String[] price = {"NULL"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        mDatabaseHelper = new DatabaseHelper(this);

        Button category,chout;
        show = (ListView) findViewById(R.id.list1);

        /*Bundle bundle = getIntent().getExtras();
        String fi[] = bundle.getStringArray("item");
        String p[] = bundle.getStringArray("price");




        for (int i = 1; i < price.length + 1; i++) {

            fooditem[i] = fi[i];
            price[i] = p[i];
            val[i] = fooditem[i] + " ( " + price[i] + " )";
            //jsonResponse += "Food Name: " + fn + "\n\n";
            //jsonResponse += "Category: " + cate + "\n\n\n";
            */
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            //get the value from database in column 1==2
            //then add it to the ArrayList
            listData.add(data.getString(1) + "\n    - RS " + data.getInt(2) + "/-");
            k=  data.getInt(2)+k;
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            show.setAdapter(adapter);

        }
        listData.add("TOTAL AMOUNT       -  RS " + k + "/-");
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        show.setAdapter(adapter);
            /*addArray.add(val[i]);

            List list = new ArrayList(Arrays.asList(val));
            list.addAll(Arrays.asList(fp));
            Object[] c = list.toArray();
            System.out.println(Arrays.toString(c));


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Cart.this, android.R.layout.simple_list_item_1, addArray);
            show.setAdapter(adapter);
            */

      /*  show.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Toast.makeText(Food.this,show.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                String sub = "" + show.getItemAtPosition(i);
                String pass[] = sub.split("\\(");
                //int p = price.length;
                String newEntry = pass[0];

                String pa[] = sub.split("\\n");
                int n2 = Integer.parseInt(pa[1]);
                Toast.makeText(Details.this,Integer.toString(n2),Toast.LENGTH_SHORT).show();
                boolean insertData = mDatabaseHelper.addData(newEntry,n2);

                if (insertData){
                    Toast.makeText(Details.this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Details.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
*/

                category = (Button) findViewById(R.id.b1);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Category_page.class);
                startActivity(intent);

               /* Bundle b = new Bundle();
                b.putStringArray("item", fooditem);
                b.putStringArray("price", price);
                Intent j = new Intent(Cart.this, Category_page.class);
                j.putExtras(b);
                startActivity(j);
                */
            }
        });
        chout = (Button) findViewById(R.id.b2);
        chout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.delData();
                Intent intent = new Intent(getApplicationContext(),CheckOut.class);
                startActivity(intent);

            }
        });

    }

}
