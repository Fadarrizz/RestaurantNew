package com.example.fadarrizz.restaurant.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fadarrizz.restaurant.R;
import com.example.fadarrizz.restaurant.menu.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuItemActivity extends AppCompatActivity {

    String name;
    String url;
    String desc;
    String price;

    TextView textView_name;
    ImageView imageView;
    TextView textView_desc;
    TextView textView_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail_item);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        name = extras.getString("name");
        url = extras.getString("url");
        desc = extras.getString("desc");
        price = extras.getString("price");
//        ArrayList menuItem = extras.getParcelableArrayList("menuItem");


        textView_name = findViewById(R.id.textView_detail_name);
        imageView = findViewById(R.id.imageView_detail_url);
        textView_desc = findViewById(R.id.textView_detail_desc);
        textView_price = findViewById(R.id.textView_detail_price);

        textView_name.setText(name);
        Picasso.get().load(url).into(imageView);
        textView_desc.setText(desc);
        textView_price.setText(price);
    }
}
