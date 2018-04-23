package com.example.fadarrizz.restaurant.menu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fadarrizz.restaurant.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<MenuItem> {

    Context context;
    int resource;
    ArrayList<MenuItem> menuItems;

    public CustomListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> menuItems) {
        super(context, resource, menuItems);
        this.context = context;
        this.resource = resource;
        this.menuItems = menuItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item, null, true);
        }

            MenuItem menuItem = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageView_url);
            TextView textView_name = convertView.findViewById(R.id.textView_name);
            TextView textView_price = convertView.findViewById(R.id.textView_price);

            textView_name.setText(menuItem.getName());
            textView_price.setText(menuItem.getPrice());
            Picasso.get().load(menuItem.getImageUrl()).into(imageView);

            return convertView;
        }
    }
