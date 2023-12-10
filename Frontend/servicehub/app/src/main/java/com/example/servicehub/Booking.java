package com.example.servicehub;

public class Booking {
    private int id;
    private int userId;
    private int carpoolListingId;

    // Constructor, getters, and setters
    public Booking(int id, int userId, int carpoolListingId) {
        this.id = id;
        this.userId = userId;
        this.carpoolListingId = carpoolListingId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarpoolListingId() {
        return carpoolListingId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCarpoolListingId(int carpoolListingId) {
        this.carpoolListingId = carpoolListingId;
    }
}
