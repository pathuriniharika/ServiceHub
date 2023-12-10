package onetoone.Bookings;

import javax.persistence.*;
import java.time.LocalDateTime;

//Represents a booking with fields for id, serviceType, tutorName, subjectName, and timeSlot.
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;           // Unique identifier for each booking
    private String serviceType; // Type of service (e.g., tutoring, plumbing, etc.)
    private String tutorName;   // Name of the tutor associated with this booking
    private String subjectName; // Name of the subject for the booking
    private LocalDateTime timeslot;    // Time slot for the booking

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

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDateTime getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(LocalDateTime timeslot) {
        this.timeslot = timeslot;
    }
}
