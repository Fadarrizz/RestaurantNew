package com.example.fadarrizz.restaurant.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fadarrizz.restaurant.R;
import com.example.fadarrizz.restaurant.categories.CategoriesActivity;
import com.example.fadarrizz.restaurant.categories.CategoriesRequest;
import com.example.fadarrizz.restaurant.details.MenuItemActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    private ListView listView;
    public String category;
    ArrayList<MenuItem> menuItem;

    MenuItem item = new MenuItem();

    MenuItemsRequest menuItemsRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        menuItemsRequest = new MenuItemsRequest(this);
        menuItemsRequest.getMenuItems(this, category);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menu) {
        this.menuItem = menu;
        CustomListViewAdapter adapterMenu = new CustomListViewAdapter(
                this, R.layout.menu_item, menu);
        listView = findViewById(R.id.menuListView);
        listView.setAdapter(adapterMenu);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
                Bundle extras = new Bundle();

                item = menuItem.get(position);
                String name = item.getName();
                String url = item.getImageUrl();
                String desc = item.getDescription();
                String price = item.getPrice();

                extras.putString("name", name);
                extras.putString("url", url);
                extras.putString("desc", desc);
                extras.putString("price", price);

                intent.putExtras(extras);

                startActivity(intent);
            }
        });
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
