package com.example.fadarrizz.restaurant.menu;

import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fadarrizz.restaurant.categories.CategoriesRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menu);
        void gotMenuItemsError(String message);
    }

    private Context context;
    private Callback activity;
    private String category;

    DecimalFormat df = new DecimalFormat("#.00");

    ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

    public MenuItemsRequest(Context context) {
        this.context = context;
    }

    public void getMenuItems(Callback activity, String category) {
        this.activity = activity;
        this.category = category;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray menuItems = response.getJSONArray("items");
            for (int i = 0; i < menuItems.length(); i++) {
                JSONObject item = menuItems.getJSONObject(i);
                if (item.getString("category").equals(category)) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(item.getString("name"));
                    Integer price = item.getInt("price");
                    menuItem.setPrice("€ " + df.format(price));
                    menuItem.setImageUrl(item.getString("image_url"));
                    menuItem.setDescription(item.getString("description"));
                    menuList.add(menuItem);
                }
            }
            activity.gotMenuItems(menuList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
