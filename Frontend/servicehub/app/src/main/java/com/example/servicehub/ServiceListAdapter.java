package com.example.servicehub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ServiceViewHolder> {

    private final Context context;
    private final List<ServiceItem> serviceItemList;
    private final OnDeleteClickListener onDeleteClickListener;

    public ServiceListAdapter(Context context, List<ServiceItem> serviceItemList, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.serviceItemList = serviceItemList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_update, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceItem serviceItem = serviceItemList.get(position);

        holder.startLocationTextView.setText("Start Location: " + serviceItem.getStartLocation());
        holder.destinationTextView.setText("Destination: " + serviceItem.getDestination());
        holder.dateTextView.setText("Date: " + serviceItem.getDateTime());
        holder.capacityTextView.setText("Capacity: " + serviceItem.getCapacity());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(serviceItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceItemList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        public TextView startLocationTextView;
        public TextView destinationTextView;
        public TextView dateTextView;
        public TextView capacityTextView;
        public Button deleteButton;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            startLocationTextView = itemView.findViewById(R.id.startLocationTextView);
            destinationTextView = itemView.findViewById(R.id.destinationTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            capacityTextView = itemView.findViewById(R.id.capacityTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int carpoolingId);
    }
}
