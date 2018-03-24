package com.example.tanis.budget_buddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListExpenses extends AppCompatActivity {

    ListView listExpenses;
    List<ExpenseNRevenueInfo> expenses = new ArrayList<ExpenseNRevenueInfo>();
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    Cursor cursor;
    String description,currency;
    Double amount;
    ExpenseNRevenueInfo expenseNRevenueInfo;
    Intent intent;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenses);
        intent = getIntent();
        type = intent.getStringExtra("type");
        listExpenses = (ListView)findViewById(R.id.listExpenses);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        addItemsToList();

        CustomListAdapter customListAdapter = new CustomListAdapter(getApplicationContext(),expenses);
        listExpenses.setAdapter(customListAdapter);

        listExpenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getApplicationContext(), "in Item click", Toast.LENGTH_SHORT).show();
                intent = new Intent(ListExpenses.this,EditnDeleteRecord.class);
                intent.putExtra("description",expenses.get(position).getDescription());
                intent.putExtra("type",type);
                startActivity(intent);

            }});
    }

    public void addItemsToList()
    {
         cursor = sqLiteDatabase.query("expensensummary", new String[] { "description",
                "amount", "currency"}, "type = ?", new String[] {type}, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // do what you need with the cursor here
            description = cursor.getString(0);
            amount = Double.parseDouble(cursor.getString(1));
            currency = cursor.getString(2);
            expenseNRevenueInfo = new ExpenseNRevenueInfo(description, amount, currency);
            expenses.add(expenseNRevenueInfo);
        }

    }

}
