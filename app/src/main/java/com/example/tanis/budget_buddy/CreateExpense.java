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
import android.widget.TextView;
import android.widget.Toast;

public class CreateExpense extends AppCompatActivity {

    TextView month;
    EditText edtTxtDesc,edtTxtAmount;
    Spinner spinnerCurrencySelector, dateselector;
    Button btnAddExpense;
    public  String desc,amount,currency,datevalue,monthValue;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Intent intent;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        intent = getIntent();
        // month = (TextView) findViewById(R.id.month);
        type = intent.getStringExtra("type");
        edtTxtAmount = (EditText)findViewById(R.id.editTxtAmount);
        edtTxtDesc = (EditText)findViewById(R.id.editTxtDesc);
        spinnerCurrencySelector= (Spinner)findViewById(R.id.spinnerCurrency);
        dateselector= (Spinner)findViewById(R.id.date);
        btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
        btnAddExpense.setText("Add "+type);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desc = edtTxtDesc.getText().toString();
                amount = edtTxtAmount.getText().toString();
                currency = spinnerCurrencySelector.getSelectedItem().toString();
   //Pushing the values in database
                contentValues.put("description",desc);
                contentValues.put("amount",amount);
                contentValues.put("currency",currency);
                contentValues.put("type",type);
                datevalue = dateselector.getSelectedItem().toString();
               // month.setText(datevalue);
               contentValues.put("date",datevalue);
                sqLiteDatabase.insert("expensensummary", null, contentValues);
                contentValues.clear();
                Toast.makeText(getApplicationContext(), type+"Data Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateExpense.this,MainActivity.class));
            }
        });




    }
}
