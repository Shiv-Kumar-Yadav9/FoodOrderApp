package com.example.logic.eathome;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Logic on 17-12-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME ="Order_Details";
    private static final String COL1 = "ID";
    private static final String COL2 = "Food_Name";
    private static final String COL3 = "Price";
    //private static final String COL3 = "Quantity";
    public DatabaseHelper(Context context ) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 +" TEXT," + COL3 +" INTEGER)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, int item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3,item2);
        Log.d(TAG, "addDatata : Adding " + item +"and "+ item2 + " TO " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
    } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void delData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String del = "DELETE FROM " + TABLE_NAME;
        db.execSQL(del);
    }
}
