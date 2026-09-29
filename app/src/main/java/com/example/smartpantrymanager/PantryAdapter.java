package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PantryAdapter extends ArrayAdapter<PantryItem> {
    public PantryAdapter(Context context, ArrayList<PantryItem> pantryItems){
        super(context, 0, pantryItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PantryItem pantryItem = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pantry,parent,false);

        }
        TextView textItemName =
                convertView.findViewById(R.id.textItemName);

        TextView textItemCategory =
                convertView.findViewById(R.id.textItemCategory);

        TextView textItemQuantity =
                convertView.findViewById(R.id.textItemQuantity);

        TextView textItemExpiry =
                convertView.findViewById(R.id.textItemExpiry);

        if (pantryItem != null){
            textItemName.setText(pantryItem.getName());

            textItemCategory.setText(
                    "Category: " + pantryItem.getCategory()
            );
            textItemQuantity.setText("Quantity: " +
                    pantryItem.getQuantity() +
                    " " +
                    pantryItem.getUnit()
            );

            textItemExpiry.setText(
                    "Expiry: " + pantryItem.getExpiryDate()
            );

        }
        return convertView;

    }
}
