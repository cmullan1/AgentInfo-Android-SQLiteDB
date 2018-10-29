/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    DBHelper.java defines the DBHelper class which contains methods for
    checking whether the database exists in the device's data folder, and
    copying it there if necessary.
 */

package com.example.a71374.day13assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TravelExpertsSqlLite.db";
    private static final String PATH = "/data/data/com.example.a71374.day13assignment/databases/";
    private static final int VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    // The copyDatabase() method copies the TravelExperts SQLite database from the project's
    // assets folder to the device's /data/data/<package name>/databases/ directory.
    public void copyDatabase(){
        boolean exists = checkDatabase();
        if (!exists)
        {
            this.getReadableDatabase();
            try {
                InputStream inputStream = context.getAssets().open(DATABASE_NAME);
                OutputStream outputStream = new FileOutputStream(PATH + DATABASE_NAME);
                byte [] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0)
                {
                    outputStream.write(buffer);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // The checkDatabase() method checks whether the TravelExpertsSQLite.db database exists
    // on the device in the location given by the PATH and DATABASE_NAME contstants.
    private boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //e.printStackTrace();

            // The database will not exist in the /data/data/.../databases/ directory on the device
            // the first time the app is run.  Do not give an error or print the stack trace.
            // In this case, copyDatabase() will create a new copy of the database from the project's
            // assets folder.
        }
        if (db != null)
        {
            db.close();
        }
        return db != null ? true : false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
