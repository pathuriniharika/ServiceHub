package com.example.servicehub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ServiceListAdapterTest {

    private ServiceListAdapter adapter;
    private List<ServiceItem> serviceItemList;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        serviceItemList = new ArrayList<>();
        serviceItemList.add(new ServiceItem(1, "Start Location 1", "Destination 1", "Date 1", 10, 1, "User 1", "Email 1"));
        serviceItemList.add(new ServiceItem(2, "Start Location 2", "Destination 2", "Date 2", 20, 2, "User 2", "Email 2"));

        adapter = new ServiceListAdapter(context, serviceItemList, new ServiceListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int carpoolingId) {
                // Implement what should happen when delete is clicked
            }
        });
    }

    @Test
    public void itemCount_isCorrect() {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void dataBinding_isCorrect() {
        ServiceListAdapter.ServiceViewHolder holder = createViewHolder();
        adapter.onBindViewHolder(holder, 0);

        assertEquals("Start Location: Start Location 1", holder.startLocationTextView.getText().toString());
        assertEquals("Destination: Destination 1", holder.destinationTextView.getText().toString());
        assertEquals("Date: Date 1", holder.dateTextView.getText().toString());
        assertEquals("Capacity: 10", holder.capacityTextView.getText().toString());
    }

    private ServiceListAdapter.ServiceViewHolder createViewHolder() {
        Context context = ApplicationProvider.getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.activity_update, null, false);
        return new ServiceListAdapter.ServiceViewHolder(itemView);
    }

    // Additional tests can be written to cover other aspects of the adapter's functionality
}

