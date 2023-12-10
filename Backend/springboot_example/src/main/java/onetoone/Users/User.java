package onetoone.Users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import onetoone.HomeService.HomeServiceListing;
import onetoone.Tutors.Tutor;
import onetoone.carpooling.CarpoolListing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author Eshanth reddy
 * 
 */ 

@Entity
public class User {

     /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "confirm_password")
    private String confirmPassword;
    private boolean ifActive;


    @Getter
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // Changed to @OneToMany
    @JsonIgnore
    private List<Tutor> tutors;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore// Changed to @OneToMany
    private List<CarpoolListing> carpoolListings;


    private String notification;
    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */









    @OneToMany(mappedBy = "lister", cascade = CascadeType.ALL)
    private List<HomeServiceListing> homeServiceListings;



    public User(String name, String email, String password, String confirmPassword, UserStatus status) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.status = status;
    }

    public User() {
        this.status = UserStatus.PROVIDER;
    }


    // =============================== Getters and Setters for each field ================================== //



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public boolean isIfActive() {
        return ifActive;
    }

    public void setIfActive(boolean ifActive) {
        this.ifActive = ifActive;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public List<CarpoolListing> getCarpoolListings() {
        return carpoolListings;
    }

    public void setCarpoolListings(List<CarpoolListing> carpoolListings) {
        this.carpoolListings = carpoolListings;
    }


    public UserStatus getStatus() {
        return status;
    }
// Getters and Setters for the new rating field
        @Getter
        @Column
        private Double rating;

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public void setStatus(UserStatus status){
            this.status = status;
        }

        public List<HomeServiceListing> getHomeServiceListings() {
            return homeServiceListings;
        }

        public void setHomeServiceListings(List<HomeServiceListing> homeServiceListings) {
            this.homeServiceListings = homeServiceListings;
        }

        public void addHomeServiceListing(HomeServiceListing listing) {
            if (homeServiceListings == null) {
                homeServiceListings = new ArrayList<>();
            }
            homeServiceListings.add(listing);
            listing.setLister(this); // Bidirectional association
        }


    public Double getRating() {
            return rating;
    }
}
