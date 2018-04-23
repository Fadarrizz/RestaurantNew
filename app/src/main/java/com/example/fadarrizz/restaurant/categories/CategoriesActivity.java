package com.example.fadarrizz.restaurant.categories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fadarrizz.restaurant.R;
import com.example.fadarrizz.restaurant.menu.MenuActivity;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView listView;
    CategoriesRequest categoriesRequest;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter adapterCategories = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, categories);
        listView = findViewById(R.id.categoryListView);
        listView.setAdapter(adapterCategories);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                category = String.valueOf(parent.getItemAtPosition(position));
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
