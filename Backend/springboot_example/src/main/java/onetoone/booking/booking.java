package onetoone.booking;


import onetoone.Tutors.Tutor;
import onetoone.Users.User;
import onetoone.carpooling.CarpoolListing;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Component("firstBooking")
public class booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User who booked the service

    @ManyToOne
    @JoinColumn(name = "carpooling_id")
    private CarpoolListing lister; // Reference to the carpooling service


    @ManyToOne
    @JoinColumn(name = "tutoring_id")
    private Tutor tutor; // Reference to the carpooling service


    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    // Constructors, getters, setters, and other fields as needed

    // Constructors
    public booking() {
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.CONFIRMED; // You can define BookingStatus enum
    }


    // Getters and setters
    // Add getters and setters for other fields as needed
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public CarpoolListing getCarpooling() {
        return lister;
    }

    public void setCarpooling(CarpoolListing lister) {
        this.lister = lister;
    }



    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }


}