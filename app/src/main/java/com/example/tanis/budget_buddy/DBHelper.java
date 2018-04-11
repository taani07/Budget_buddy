package com.example.tanis.budget_buddy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tanis on 2018-03-22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BudgetStorage";

    private static final int DATABASE_VERSION = 2;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

   //Creating the table in the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table expensensummary\n" +
                "                                 (  description text,amount real ,currency text, type text, date text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS expensensummary");
        onCreate(sqLiteDatabase);

    }
}
