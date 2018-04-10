package com.example.tanis.budget_buddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Logger;

//import static com.example.tanis.budget_buddy.MainActivity.items;

public class CustomListAdapter extends BaseAdapter {
    private Context contxt;

    private List<ExpenseNRevenueInfo> listExpensesNRevenue;
    int count;
    LayoutInflater inflater;

    public CustomListAdapter(Context contxt, List<ExpenseNRevenueInfo> listExpensesNRevenue)
    {
        this.contxt = contxt;
        this.listExpensesNRevenue = listExpensesNRevenue;
//        count = 0;
//        pos=0;
        inflater = (LayoutInflater) this.contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return listExpensesNRevenue.size();
    }

    @Override
    public Object getItem(int position) {
        return listExpensesNRevenue.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView= inflater.inflate(R.layout.expense_n_revenue_list_format,null);

        TextView desc = (TextView) convertView.findViewById(R.id.txtDesc);
        desc.setText(listExpensesNRevenue.get(position).getDescription());

        TextView amount = (TextView) convertView.findViewById(R.id.txtAmount);
        amount.setText(listExpensesNRevenue.get(position).getAmount().toString());

        TextView currency = (TextView) convertView.findViewById(R.id.txtCurrency);
        currency.setText(listExpensesNRevenue.get(position).getCurrency());
//
//        TextView month = (TextView) convertView.findViewById(R.id.txtMonth);
//        month.setText(listExpensesNRevenue.get(position).getMonth());
        return convertView;
    }
}