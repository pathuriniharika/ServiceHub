package onetoone.Bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

//Extends JpaRepository for database operations on the Booking entity.
//        Includes a custom method findByTutorNameAndTimeSlot to find bookings by tutor name and time slot.

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findById(int id);
    void deleteById(int id);

    // Custom method to find bookings by tutor name and time slot
    List<Booking> findByTutorNameAndTimeslot(String tutorName, LocalDateTime timeslot);
}
