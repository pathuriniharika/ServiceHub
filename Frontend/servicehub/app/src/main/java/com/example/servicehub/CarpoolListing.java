package com.example.servicehub;

public class CarpoolListing {
    private String startLocation;
    private String destination;
    private String dateTime;
    private int capacity;
    private int id;
    private double averageRating;

    public CarpoolListing(int id, String startLocation, String destination, String dateTime, int capacity, double averageRating) {
        this.id = id;
        this.startLocation = startLocation;
        this.destination = destination;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.averageRating = averageRating;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}

