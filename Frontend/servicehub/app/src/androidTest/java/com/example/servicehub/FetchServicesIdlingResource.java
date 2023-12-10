package com.example.servicehub;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class FetchServicesIdlingResource {

    private static final String RESOURCE_NAME = "FetchServicesIdlingResource";

    // Use CountingIdlingResource for simplicity
    private static final CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource(RESOURCE_NAME);

    // Method to increment the counter
    public static void increment() {
        countingIdlingResource.increment();
    }

    // Method to decrement the counter
    public static void decrement() {
        countingIdlingResource.decrement();
    }

    // Method to get the IdlingResource
    public static IdlingResource getIdlingResource() {
        return countingIdlingResource;
    }

    // Method to mock a network response
    public static void mockResponse(String response) {
        // Simulate network delay (you can adjust this based on your needs)
        countingIdlingResource.increment();

        // Perform the actual response mocking
        // For example, using a mocking library or setting up a mock server

        // After mocking the response, decrement the counter
        countingIdlingResource.decrement();
    }
}
