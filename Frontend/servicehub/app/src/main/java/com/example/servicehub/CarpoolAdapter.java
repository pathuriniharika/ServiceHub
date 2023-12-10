package com.example.servicehub;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarpoolAdapter extends RecyclerView.Adapter<CarpoolAdapter.CarpoolListingViewHolder> {
    private List<CarpoolListing> carpoolListings;
    private OnJoinButtonClickListener onJoinButtonClickListener;
    private OnRateButtonClickListener onRateButtonClickListener;
    private int userId;
    private boolean showJoinButton;
    private boolean showRateButton;
    private boolean showAverageRatingTextView;



    public interface OnJoinButtonClickListener {
        void onJoinButtonClick(int position, int userId, int carpoolListingId);
    }

    public interface OnRateButtonClickListener {
        void onRateButtonClick(int position, int carpoolListingId);
    }

    public CarpoolAdapter(List<CarpoolListing> carpoolListings, OnJoinButtonClickListener onJoinButtonClickListener, OnRateButtonClickListener onRateButtonClickListener, int userId, boolean showJoinButton, boolean showRateButton, boolean showAverageRatingTextView) {
        this.carpoolListings = carpoolListings;
        this.onJoinButtonClickListener = onJoinButtonClickListener;
        this.onRateButtonClickListener = onRateButtonClickListener;
        this.userId = userId;
        this.showJoinButton = showJoinButton;
        this.showRateButton = showRateButton;
        this.showAverageRatingTextView = showAverageRatingTextView; // Initialize the field
    }

    @NonNull
    @Override
    public CarpoolListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carpool_card_item, parent, false);
        return new CarpoolListingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarpoolListingViewHolder holder, int position) {
        CarpoolListing carpoolListing = carpoolListings.get(position);
        holder.idCarpoolTextView.setText("Id: " + carpoolListing.getId());
        holder.startLocationTextView.setText("Start Location: " + carpoolListing.getStartLocation());
        holder.destinationTextView.setText("Destination: " + carpoolListing.getDestination());
        holder.dateTimeTextView.setText("Date & Time: " + carpoolListing.getDateTime());
        holder.capacityTextView.setText("Capacity: " + carpoolListing.getCapacity());
        holder.averageRatingTextView.setText("Rating: " + carpoolListing.getAverageRating());

        holder.joinButton.setVisibility(showJoinButton ? View.VISIBLE : View.GONE);
        if (showJoinButton) {
            holder.joinButton.setOnClickListener(v -> onJoinButtonClickListener.onJoinButtonClick(holder.getAdapterPosition(), userId, carpoolListing.getId()));
        }

        holder.rateButton.setVisibility(showRateButton ? View.VISIBLE : View.GONE);
        holder.rateButton.setOnClickListener(v -> {
            if (onRateButtonClickListener != null) {
                onRateButtonClickListener.onRateButtonClick(holder.getAdapterPosition(), carpoolListing.getId());
            }
        });

        // Control the visibility of the AverageRatingTextView based on the showAverageRatingTextView parameter
        holder.averageRatingTextView.setVisibility(showAverageRatingTextView ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return carpoolListings.size();
    }

    // Inside CarpoolAdapter class
    public int getCarpoolId(int position) {
        // Make sure the position is valid
        if (position >= 0 && position < carpoolListings.size()) {
            return carpoolListings.get(position).getId();
        } else {
            // Handle invalid position if necessary
            return -1;
        }
    }

    public void updateCarpoolListingRating(int carpoolId, double averageRating) {
        for (int i = 0; i < carpoolListings.size(); i++) {
            CarpoolListing listing = carpoolListings.get(i);
            if (listing.getId() == carpoolId) {
                listing.setAverageRating(averageRating);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public static class CarpoolListingViewHolder extends RecyclerView.ViewHolder {
        TextView startLocationTextView, destinationTextView, dateTimeTextView, capacityTextView, idCarpoolTextView, averageRatingTextView;
        Button joinButton, rateButton;

        public CarpoolListingViewHolder(View itemView) {
            super(itemView);
            startLocationTextView = itemView.findViewById(R.id.startLocationTextView);
            destinationTextView = itemView.findViewById(R.id.destinationTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
            capacityTextView = itemView.findViewById(R.id.capacityTextView);
            idCarpoolTextView = itemView.findViewById(R.id.id_carpool);
            averageRatingTextView = itemView.findViewById(R.id.websocketMessageTextView);
            joinButton = itemView.findViewById(R.id.joinButton);
            rateButton = itemView.findViewById(R.id.RateButton);
        }
    }
}
