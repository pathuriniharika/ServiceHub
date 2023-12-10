package com.example.servicehub;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CarpoolAdapterTest {

    private CarpoolAdapter adapter;
    private List<CarpoolListing> carpoolListings;

    @Before
    public void setUp() {
        carpoolListings = new ArrayList<>();
        // Add carpool listings to the list
        adapter = new CarpoolAdapter(carpoolListings, null, null, 0, false, false, false);
    }

    @Test
    public void testGetItemCount() {
        int itemCount = adapter.getItemCount();
        assertEquals(carpoolListings.size(), itemCount);
    }

    @Test
    public void testGetCarpoolId() {
        // Add carpool listings to the list (modify this based on your test data)
        CarpoolListing carpoolListing1 = new CarpoolListing(1, "Start1", "Destination1", "2023-12-31", 3, 4.5);
        carpoolListings.add(carpoolListing1);

        int position = 0;
        int expectedCarpoolId = carpoolListings.get(position).getId();
        int actualCarpoolId = adapter.getCarpoolId(position);
        assertEquals(expectedCarpoolId, actualCarpoolId);
    }
}
