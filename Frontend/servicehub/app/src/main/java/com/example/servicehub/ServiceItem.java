package com.example.servicehub;
import android.annotation.SuppressLint;
import android.app.Activity;

public class ServiceItem {
    private int id;
    private String startLocation;
    private String destination;
    private String dateTime;
    private int capacity;
    private int userId;
    private String userName;
    private String userEmail;

    public ServiceItem() {

    }

    public ServiceItem(int id, String startLocation, String destination, String dateTime, int capacity, int userId, String userName, String userEmail) {
        this.id = id;
        this.startLocation = startLocation;
        this.destination = destination;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

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

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
