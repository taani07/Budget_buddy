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

import java.util.logging.Logger;


public class CustomGridAdapter extends BaseAdapter {
    private Context contxt;

    private String[] items;
    int count;
    LayoutInflater inflater;

    //adapter for Grid View
    public CustomGridAdapter(Context contxt, String[] items)
    {
        this.contxt = contxt;
        this.items = items;
//        count = 0;
//        pos=0;
        inflater = (LayoutInflater) this.contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView= inflater.inflate(R.layout.menubutton,null);


        TextView tv = (TextView) convertView.findViewById(R.id.btnMenu);
        tv.setText(items[position]);


        return convertView;
    }
}
