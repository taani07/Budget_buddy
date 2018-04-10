package com.example.tanis.budget_buddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExpenseNRevenueSummary extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    TextView txtSummary;
    TextView txtSummary2;
    Cursor cursor;
    DBHelper dbHelper;
    String type,summary="";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_nrevenue_summary);
        txtSummary = (TextView)findViewById(R.id.txtSummary);
        txtSummary2 = (TextView)findViewById(R.id.txtSummary2);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        intent = getIntent();
        type = intent.getStringExtra("type");
        cursor = sqLiteDatabase.query("expensensummary", new String[] {
                "sum(amount)", "currency"}, "type = ?", new String[] {type}, "currency", null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // do what you need with the cursor here
            summary  =summary + cursor.getString(0)+" " +(cursor.getString(1)) +"\n";
//            currency = cursor.getString(2);
//            expenseNRevenueInfo = new ExpenseNRevenueInfo(description, amount, currency);
//            expenses.add(expenseNRevenueInfo);
        }

        txtSummary.setText(summary);

    }
}
