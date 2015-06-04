package com.mombartz.artwalk;

import java.util.List;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Walks> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
    }

    private List<Walks> items;

    public ListAdapter(Context context, int resource, List<Walks> items) {

        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        TextView tt = null;
        TextView tt1 = null;


        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row, null);
            tt = (TextView) v.findViewById(R.id.walks);
            tt1 = (TextView) v.findViewById(R.id.desc);
        }

        Walks p = items.get(position);

        if (p != null) {

            if (tt != null) {
                tt.setText(""+p.getSlug());
            }
         if (tt1 != null) {

                tt1.setText(""+p.getName());
            }
        }



        return v;

    }
 
    
}