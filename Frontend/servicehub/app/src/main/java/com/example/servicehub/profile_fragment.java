package com.example.servicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatDelegate;

public class profile_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_frag, container, false);

        // Assuming you have a button with id editProfileButton in your profile_frag.xml
        Button editProfileButton = rootView.findViewById(R.id.editProfileButton);

        // Set an onClickListener for the editProfileButton
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message to check if the button click is registered
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();

                // Open the EditProfileActivity when the button is clicked
                Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
                startActivity(intent);
            }
        });



        ImageView rateAppArrow = rootView.findViewById(R.id.rateAppArrow);
        rateAppArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the RateAppActivity when the arrow is clicked
                Intent intent = new Intent(getActivity(), AppRateActivity.class);
                startActivity(intent);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView historyArrow = rootView.findViewById(R.id.bookingHistoryArrow);
        historyArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the RateAppActivity when the arrow is clicked
                Intent intent = new Intent(getActivity(), BookingHistory.class);
                startActivity(intent);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView listingHistoryArrow = rootView.findViewById(R.id.listingHistoryArrow);
        listingHistoryArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchServices.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
