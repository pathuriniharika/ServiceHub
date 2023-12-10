package onetoone.Reservation;

import onetoone.Users.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Component("secondBooking")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serviceType;
    private LocalDateTime timeslot;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lister_id")
    private User lister;

    @Column(name = "lister_id", insertable = false, updatable = false)
    private Integer listerId; // Add the lister_id field


    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(LocalDateTime timeslot) {
        this.timeslot = timeslot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLister() {
        return lister;
    }

    public void setLister(User lister) {
        this.lister = lister;
    }

    public Integer getListerId() {
        return listerId;
    }

    public void setListerId(Integer listerId) {
        this.listerId = listerId;
    }
}
