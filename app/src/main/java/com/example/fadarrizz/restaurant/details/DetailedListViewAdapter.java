package com.example.fadarrizz.restaurant.details;

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
import com.example.fadarrizz.restaurant.menu.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailedListViewAdapter extends ArrayAdapter<MenuItem> {

    Context context;
    int resource;
    ArrayList<MenuItem> menuItems;

    public DetailedListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> menuItems) {
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
            convertView = inflater.inflate(R.layout.menu_detail_item, null, true);
        }

        MenuItem menuItem = getItem(position);
        TextView textView_name = (TextView) convertView.findViewById(R.id.textView_detail_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_detail_url);
        TextView textView_desc = (TextView) convertView.findViewById(R.id.textView_detail_desc);
        TextView textView_price = (TextView) convertView.findViewById(R.id.textView_detail_price);

        textView_name.setText(menuItem.getName());
        Picasso.get().load(menuItem.getImageUrl()).into(imageView);
        textView_desc.setText(menuItem.getDescription());
        textView_price.setText(menuItem.getPrice());

        return convertView;
    }
}
