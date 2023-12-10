package com.example.servicehub;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceItemTest {

    private ServiceItem serviceItem;

    @Before
    public void setUp() {
        serviceItem = new ServiceItem(1, "StartLocation", "Destination", "DateTime", 5, 2, "UserName", "UserEmail");
    }

    @Test
    public void testIdGetter() {
        assertEquals(1, serviceItem.getId());
    }

    @Test
    public void testStartLocationGetter() {
        assertEquals("StartLocation", serviceItem.getStartLocation());
    }

    @Test
    public void testDestinationGetter() {
        assertEquals("Destination", serviceItem.getDestination());
    }

    @Test
    public void testDateTimeGetter() {
        assertEquals("DateTime", serviceItem.getDateTime());
    }

    @Test
    public void testCapacityGetter() {
        assertEquals(5, serviceItem.getCapacity());
    }

    @Test
    public void testUserIdGetter() {
        assertEquals(2, serviceItem.getUserId());
    }

    @Test
    public void testUserNameGetter() {
        assertEquals("UserName", serviceItem.getUserName());
    }

    @Test
    public void testUserEmailGetter() {
        assertEquals("UserEmail", serviceItem.getUserEmail());
    }
}

