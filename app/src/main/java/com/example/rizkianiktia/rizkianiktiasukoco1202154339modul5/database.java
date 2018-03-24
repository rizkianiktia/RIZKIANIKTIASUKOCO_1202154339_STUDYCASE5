package com.example.rizkianiktia.rizkianiktiasukoco1202154339modul5;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase data;
    public static final String db_name = "modul.db";
    public static final String table_name = "todo";
    public static final String col_1 = "name";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";

    //class database
    public database(Context context) {
        super(context, db_name, null, 1);
        this.context = context;
        data = this.getWritableDatabase();
        data.execSQL("create table if not exists " + table_name + " (name varchar(50) primary key, description varchar(50), priority varchar(5))");
    }

//method untuk membuat database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + table_name + " (name varchar(50) primary key, description varchar(50), priority varchar(5))");
    }

    //method untuk memperbaharui data
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + table_name);
        onCreate(sqLiteDatabase);

    }

    //Method  untuk memasukkan data
    public boolean insertdata(itemtodo satuan) {
        ContentValues cv = new ContentValues();
        cv.put(col_1, satuan.getNama());
        cv.put(col_2, satuan.getDeskripsi());
        cv.put(col_3, satuan.getPrioritas());
        long result = data.insert(table_name, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Method digunakan untuk menghapus data
    public boolean deletedata(String name) {
        return data.delete(table_name, col_1 + "=\"" + name + "\"", null) > 0;
    }

    //Method untuk mendapatkan data dari database
    public void getAllItems(ArrayList<itemtodo> list) {
        Cursor cr = this.getReadableDatabase().rawQuery("select name, description, priority from " + table_name, null);
        while (cr.moveToNext()) {
            list.add(new itemtodo(cr.getString(0), cr.getString(1), cr.getString(2)));
        }
    }


    }

