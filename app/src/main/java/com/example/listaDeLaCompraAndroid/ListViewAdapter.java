package com.example.listaDeLaCompraAndroid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

import java.util.ArrayList;

class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    ArrayList<Float> precios;
    Context context;

    // The ListViewAdapter Constructor
    // @param context: the Context from the MainActivity
    // @param items: The list of items in our Grocery List
    public ListViewAdapter(Context context, ArrayList<String> items, ArrayList<Float> precio) {
        super(context, R.layout.list_row, items);
        this.context = context;
        list = items;
        precios = precio;
    }

    // The method we override to provide our own layout for each View (row) in the ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row, null);
            TextView name = convertView.findViewById(R.id.name);
            TextView TextPrecio = convertView.findViewById(R.id.price);
            ImageView remove = convertView.findViewById(R.id.remove);
            ImageView copy = convertView.findViewById(R.id.copy);
            TextView number = convertView.findViewById(R.id.number);

            number.setText(position + 1 + ".");
            name.setText(list.get(position));
            TextPrecio.setText(precios.get(position) + "€");

            // Listeners for duplicating and removing an item.
            // They use the static removeItem and addItem methods created in MainActivity.
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.removeItem(position);
                }
            });
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.addItem(list.get(position), precios.get(position));
                }
            });
        }
        return convertView;
    }

}