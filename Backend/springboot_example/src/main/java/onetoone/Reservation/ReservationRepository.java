package onetoone.Reservation;

import onetoone.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//Extends JpaRepository for database operations on the Booking entity.
//        Includes a custom method findByTutorNameAndTimeSlot to find bookings by tutor name and time slot.
@Component("secondBookingRepository")
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);
    void deleteById(int id);

    List<Reservation> findByServiceType(String serviceType);
    void deleteByServiceType(String serviceType);

    List<Reservation> findByServiceTypeAndTimeslot(String serviceType, LocalDateTime timeslot);

    //findbyServiceType

    List<Reservation> findByUser(User user);

    List<Reservation> findByUserId(int userId);



}

