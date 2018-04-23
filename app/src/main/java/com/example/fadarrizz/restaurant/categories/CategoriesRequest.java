package com.example.fadarrizz.restaurant.categories;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fadarrizz.restaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Properties of the class
    private Context context;
    private Callback activity;

    ArrayList<String> categoryList = new ArrayList<String>();

    // Constructor of the class
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Methods of the class
    public void getCategories(Callback activity) {
        this.activity = activity;

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, this, this);

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categories;
        try {
            categories = response.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                categoryList.add(categories.getString(i));
            }
            activity.gotCategories(categoryList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
