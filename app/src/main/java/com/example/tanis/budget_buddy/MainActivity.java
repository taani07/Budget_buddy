package com.example.tanis.budget_buddy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity{

    GridView gridMenu;
    public static final String TYPE = "type";
    public static String[] items = new String[6];
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridMenu = (GridView)findViewById(R.id.gridMenu);
        items[0]="Create Expense";
        items[1]="Create Revenue";
        items[2]="List Expenses";
        items[3]="List Revenues";
        items[4]="Expense summary";
        items[5]="Revenue summary";

        CustomGridAdapter cga = new CustomGridAdapter(MainActivity.this,items);
        gridMenu.setAdapter(cga);

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "in Item click", Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0:
                        intent = new Intent(MainActivity.this,CreateExpense.class);
                        intent.putExtra(TYPE,"Expense");
                        startActivity(intent);
//                        startActivity(new Intent(MainActivity.this,CreateExpense.class));
                        Toast.makeText(getApplicationContext(), "in case 0", Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this,CreateExpense.class);
                        intent.putExtra(TYPE,"Revenue");
                        startActivity(intent);
//                        startActivity(new Intent(MainActivity.this,CreateExpense.class));
                        Toast.makeText(getApplicationContext(), "in case 0", Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        intent = new Intent(MainActivity.this,ListExpenses.class);
//                        startActivity(new Intent(MainActivity.this,ListExpenses.class));
//                        Toast.makeText(getApplicationContext(), "in case 2", Toast.LENGTH_LONG).show();
                        intent.putExtra(TYPE,"Expense");
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(MainActivity.this,ListExpenses.class);
//                        startActivity(new Intent(MainActivity.this,ListExpenses.class));
//                        Toast.makeText(getApplicationContext(), "in case 2", Toast.LENGTH_LONG).show();
                        intent.putExtra(TYPE,"Revenue");
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(MainActivity.this,ExpenseNRevenueSummary.class);
                        intent.putExtra(TYPE,"Expense");
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(MainActivity.this,ExpenseNRevenueSummary.class);
                        intent.putExtra(TYPE,"Revenue");
                        startActivity(intent);
                        break;
                }
            }
            });

    }
}
