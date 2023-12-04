package com.dearmahmud.firstdatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final  String DB_NAME = "mydb";
    private static final  int DB_VERSION = 1;
    private static final  String TABLE_NAME = "information";
    private static final  String ID_COL="tid";
    private static final  String NAME_COL="tname";
    private static final  String DEPT_COL="tdept";
    private static final  String SESSION_COL="tsession";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB_VERSION) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID_COL + " TEXT, " +
                NAME_COL + " TEXT, " +
                DEPT_COL + " TEXT, " +
                SESSION_COL + " TEXT" +
                ")";
        DB_VERSION.execSQL(query);
    }

    public void addData(String id, String name, String dept, String session){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_COL, id);
        values.put(NAME_COL, name);
        values.put(DEPT_COL, dept);
        values.put(SESSION_COL, session);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result == -1) {
            Log.e("DBHandler", "Failed to insert data into table");
        } else {
            Log.d("DBHandler", "Data inserted successfully");
        }
    }

    public String getAllData() {
        StringBuilder data = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ID_COL));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME_COL));
                @SuppressLint("Range") String dept = cursor.getString(cursor.getColumnIndex(DEPT_COL));
                @SuppressLint("Range") String session = cursor.getString(cursor.getColumnIndex(SESSION_COL));

                // Append the retrieved data to the StringBuilder
                data.append("ID: ").append(id)
                    .append(", Name: ").append(name)
                    .append(", Dept: ").append(dept)
                    .append(", Session: ").append(session)
                    .append("\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return data.toString();
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
        Log.d("DBHandler", "All data deleted successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
