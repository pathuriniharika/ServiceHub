package onetoone.HomeService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import onetoone.Users.User;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "home_service_listing")
public class HomeServiceListing {

    @Getter
    @ManyToOne
    @JoinColumn(name = "lister_id")
    @JsonIgnoreProperties("homeServiceListings") // Add this annotation to break serialization loop
    private User lister;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    private String serviceType;
    @Getter
    private String description;
    @Getter
    private double price;

//    @Getter
//    private LocalDateTime timeslot;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Assuming this field represents the user associated with this listing
    private User user;



    // Add a no-arg constructor
    public HomeServiceListing() {
    }

    // Constructors, getters, and setters (similar to the Tutor entity)

    // Example constructor:
    public HomeServiceListing(String serviceType, String description, double price) {
        this.serviceType = serviceType;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters for all fields

    public void setId(int id) {
        this.id = id;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLister(User lister) {
        this.lister = lister;
    }

//    public void setTimeslot(LocalDateTime timeslot) {
//        this.timeslot = timeslot;
//    }

    public User getLister() {
        return lister;
    }

    public int getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

//    public LocalDateTime getTimeslot() {
//        return timeslot;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
