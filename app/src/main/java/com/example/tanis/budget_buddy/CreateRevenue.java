package com.example.tanis.budget_buddy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateRevenue extends AppCompatActivity {

    EditText edtTxtDesc,edtTxtAmount;
    Spinner spinnerCurrencySelector;
    Button btnAddExpense;
    String desc,amount,currency;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_revenue);

        edtTxtAmount = (EditText)findViewById(R.id.editTxtAmountRvnue);
        edtTxtDesc = (EditText)findViewById(R.id.editTxtDescRvnue);
        spinnerCurrencySelector= (Spinner)findViewById(R.id.spinnerCurrencyRvnue);
        btnAddExpense = (Button) findViewById(R.id.btnAddRevenue);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desc = edtTxtDesc.getText().toString();
                amount = edtTxtAmount.getText().toString();
                currency = spinnerCurrencySelector.getSelectedItem().toString();
                contentValues.put("description",desc);
                contentValues.put("amount",amount);
                contentValues.put("currency",currency);
                contentValues.put("type","revenue");
                sqLiteDatabase.insert("expensensummary", null, contentValues);
                contentValues.clear();
                Toast.makeText(getApplicationContext(), "Revenue Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateRevenue.this,MainActivity.class));


            }
        });

    }
}
