package com.example.servicehub;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CarpoolListingTest {

    @Test
    public void testCarpoolListingProperties() {
        int id = 1;
        String startLocation = "Start";
        String destination = "Destination";
        String dateTime = "2023-12-31";
        int capacity = 3;
        double averageRating = 4.5;

        CarpoolListing carpoolListing = new CarpoolListing(id, startLocation, destination, dateTime, capacity, averageRating);

        // Test getter methods
        assertEquals(id, carpoolListing.getId());
        assertEquals(startLocation, carpoolListing.getStartLocation());
        assertEquals(destination, carpoolListing.getDestination());
        assertEquals(dateTime, carpoolListing.getDateTime());
        assertEquals(capacity, carpoolListing.getCapacity());
        assertEquals(averageRating, carpoolListing.getAverageRating(), 0.01);

        // Test setter methods
        int newId = 2;
        carpoolListing.setId(newId);
        assertEquals(newId, carpoolListing.getId());

        String newStartLocation = "New Start";
        carpoolListing.setStartLocation(newStartLocation);
        assertEquals(newStartLocation, carpoolListing.getStartLocation());


    }
}
