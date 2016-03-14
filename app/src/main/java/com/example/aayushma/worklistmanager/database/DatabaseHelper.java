package com.example.aayushma.worklistmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aayushma on 2/29/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="db_todo";
    public static final String TABLE="todos";
    public static final String ID="id";
    public static final String LISTS="lists";
    public static final String DESCRIPTION="description";
    public static final String DATEANDTIME="dateandtime";
    public static final String SQL_CREATE="CREATE TABLE "
            + TABLE
            + " ( "+ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + LISTS + " TEXT, " + DESCRIPTION + " TEXT, " + DATEANDTIME + " TEXT "  + " )";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
