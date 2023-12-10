package onetoone.carpooling;

import com.fasterxml.jackson.annotation.JsonProperty;
import onetoone.Users.User;

import javax.persistence.*;

@Entity
public class CarpoolListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "avg_rating")
    private double avgRating;
    private String startLocation;
    private String destination;

    private int capacity;
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the CarpoolListing table
    private User user;

    public CarpoolListing(String startLocation, String destination, int capacity, String dateTime, double avgRating) {
        this.startLocation = startLocation;
        this.destination = destination;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.avgRating = avgRating;
    }

    public CarpoolListing() {
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    @JsonProperty("avgRating")
    public double getAvgRating() {
        return avgRating;
    }
}
