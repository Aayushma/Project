package com.example.aayushma.worklistmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.aayushma.worklistmanager.Todos;

import java.util.ArrayList;

/**
 * Created by aayushma on 2/29/2016.
 */
public class DatabaseManager {
    public static SQLiteDatabase db;
    public static DatabaseManager DbManager;

    private DatabaseManager(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public static DatabaseManager getDbManager(Context context) {
        if (DbManager == null) {
            DbManager = new DatabaseManager(context);
        }
        return DbManager;
    }


    public static void insertData(Todos todos) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.LISTS, todos.getLists());
        values.put(DatabaseHelper.DESCRIPTION, todos.getDescription());
        values.put(DatabaseHelper.DATEANDTIME, todos.getDatetime());
        db.insert(DatabaseHelper.TABLE, null, values);
    }
    public ArrayList<Todos> fetchContact() {
        String select = "select * from " + DatabaseHelper.TABLE;
        Cursor c = db.rawQuery(select, null);


        ArrayList<Todos> list=new ArrayList<>();
        while (c.moveToNext()){
            Todos todos=new Todos();
            todos.setLists(c.getString(c.getColumnIndex(DatabaseHelper.LISTS)));
            todos.description = c.getString(c.getColumnIndex(DatabaseHelper.DESCRIPTION));
            todos.datetime = c.getString(c.getColumnIndex(DatabaseHelper.DATEANDTIME));

            Log.v("title",todos.getLists());
            list.add(todos);
        }

        c.close();



        return list;
    }

    public void DeleteFromDb(String title){

        String sqlquery="DELETE FROM " + DatabaseHelper.TABLE + " WHERE " +DatabaseHelper.LISTS +" = '" + title + "'";
        db.execSQL(sqlquery);
    }

    public void UpdateDb(Todos todos){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.LISTS, todos.getLists());
        values.put(DatabaseHelper.DESCRIPTION, todos.getDescription());
        values.put(DatabaseHelper.DATEANDTIME, todos.getDatetime());
        db.update(DatabaseHelper.TABLE, values, DatabaseHelper.ID +"= ?", new String[]{String.valueOf((todos.getId()))});
    }
}

