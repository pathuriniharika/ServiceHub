package com.example.servicehub;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class update_booking extends Activity implements ServiceListAdapter.OnDeleteClickListener {

    private RecyclerView recyclerView;
    private ServiceListAdapter adapter;
    private List<ServiceItem> serviceItems = new ArrayList<>();
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_recycle);

        recyclerView = findViewById(R.id.serviceRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceListAdapter(this, serviceItems, this);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        fetchServiceList();
    }

    private void fetchServiceList() {
        int userId = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE).getInt("userId", 0);
        String url = "http://coms-309-063.class.las.iastate.edu:8080/listings/byUser/" + userId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject serviceJson = response.getJSONObject(i);
                                int id = serviceJson.getInt("id");
                                String startLocation = serviceJson.getString("startLocation");
                                String destination = serviceJson.getString("destination");
                                String dateTime = serviceJson.getString("dateTime");
                                int capacity = serviceJson.getInt("capacity");

                                JSONObject userObject = serviceJson.getJSONObject("user");
                                int userId = userObject.getInt("id");
                                String userName = userObject.getString("name");
                                String userEmail = userObject.getString("email");

                                ServiceItem serviceItem = new ServiceItem(id, startLocation, destination, dateTime, capacity, userId, userName, userEmail);
                                serviceItems.add(serviceItem);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    public void onDeleteClick(int carpoolingId) {

        String deleteUrl = "http://coms-309-063.class.las.iastate.edu:8080/deleteByCarpoolingId/" + carpoolingId;


        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        removeItemFromList(carpoolingId);

                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Carpool listing deleted", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Error deleting carpool listing", Toast.LENGTH_SHORT).show();
                        Log.e("DeleteError", "Error deleting carpool listing: " + error.toString());
                    }
                });


        requestQueue.add(deleteRequest);
    }


    private void removeItemFromList(int carpoolingId) {
        for (ServiceItem item : serviceItems) {
            if (item.getId() == carpoolingId) {
                serviceItems.remove(item);
                break;
            }
        }
    }
}
