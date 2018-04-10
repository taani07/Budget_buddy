package com.example.tanis.budget_buddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpenseNRevenueSummary extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    ExpenseNRevenueInfo expenseNRevenueInfo;
    List<ExpenseNRevenueInfo> expenses = new ArrayList<ExpenseNRevenueInfo>();
    ListView txtSummary;
    TextView txtSummary2;
    Cursor cursor;
    DBHelper dbHelper;
    String description,currency;
    Double amount;
    String type,summary="",month;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_nrevenue_summary);
        txtSummary = (ListView)findViewById(R.id.txtSummary);
       // txtSummary2 = (TextView)findViewById(R.id.txtSummary2);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        intent = getIntent();
        type = intent.getStringExtra("type");

        addItemsToList();

        CustomListAdapter customListAdapter = new CustomListAdapter(getApplicationContext(),expenses);
        txtSummary.setAdapter(customListAdapter);
//        cursor = sqLiteDatabase.query("expensensummary", new String[] {
//                "sum(amount)", "currency"}, "type = Expense", null, "currency", null, null);

    }

    public void addItemsToList()
    {  cursor = sqLiteDatabase.query("expensensummary", new String[] {
                "sum(amount)", "currency","date"}, "type = ?", new String[] {type}, "date", null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // do what you need with the cursor here
            //description = cursor.getString(0);
            amount = Double.parseDouble(cursor.getString(0));
            currency = cursor.getString(1);
            month = cursor.getString(2);
           expenseNRevenueInfo = new ExpenseNRevenueInfo(description, amount, currency, month);
            expenses.add(expenseNRevenueInfo);
        }


    }
}
