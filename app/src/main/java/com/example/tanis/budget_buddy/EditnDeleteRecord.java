package com.example.tanis.budget_buddy;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EditnDeleteRecord extends AppCompatActivity implements View.OnClickListener {

    Button btnDelete;
    EditText edtTxtDesc,edtTxtAmount;
    Spinner spinnerCurrencySelector;
    Button btnAddExpense;
    Intent intent;
    String type,desc;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        edtTxtDesc = (EditText) findViewById(R.id.editTxtDesc);
        edtTxtAmount = (EditText)findViewById(R.id.editTxtAmount);
        spinnerCurrencySelector = (Spinner) findViewById(R.id.spinnerCurrency);
        btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
        btnDelete.setVisibility(View.VISIBLE);
        intent = getIntent();
        type = intent.getStringExtra("type");
        desc = intent.getStringExtra("description");
        btnAddExpense.setText("Update "+type);

        btnAddExpense.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        setEditTexts();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAddExpense:
                updateRecord();
                break;

            case R.id.btnDelete:
                deleteRecord();
                break;

        }

    }

    public void setEditTexts()
    {
        cursor = sqLiteDatabase.query("expensensummary", new String[] { "description",
                "amount", "currency"}, "type = ? AND description = ?", new String[] {type,desc}, null, null, null);
        if(cursor.moveToFirst()){
            edtTxtDesc.setText(desc);
            edtTxtAmount.setText(cursor.getString(1));

            //implement spinner selection
//            spinnerCurrencySelector.setSe
//            SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinnerCurrencySelector.getAdapter();
//            for (int position = 0; position < adapter.getCount(); position++) {
//                if(spinnerCurrencySelector.getItemAtPosition(position).toString() == cursor.getString(2)) {
//                    spinnerCurrencySelector.setSelection(position);
//                    break;
//                }
//            }

        }

        edtTxtDesc.setEnabled(false);

    }

    public void updateRecord()
    {

        contentValues.put("amount",edtTxtAmount.getText().toString());
        contentValues.put("currency",spinnerCurrencySelector.getSelectedItem().toString());

        sqLiteDatabase.update("expensensummary", contentValues,
                "type = ? AND description = ?", new String[] {type,desc});

        Toast.makeText(getApplicationContext(),type+" updated",Toast.LENGTH_SHORT).show();
        intent = new Intent(EditnDeleteRecord.this,ListExpenses.class);
        intent.putExtra("type",type);
        startActivity(intent);



    }

    public void deleteRecord()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Delete "+type);
        alert.setMessage("Are you sure you want to delete "+type+"?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                sqLiteDatabase.delete("expensensummary","type = ? AND description = ?",new String[]{type,desc});
                Toast.makeText(getApplicationContext(),"Deleted "+type,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditnDeleteRecord.this,MainActivity.class));
            }
        });
        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        alert.show();
    }
}
