package onetoone.booking;

import onetoone.Users.User;
import onetoone.carpooling.CarpoolListing;
import onetoone.carpooling.CarpoolListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class bookingService {

    @Autowired
    private onetoone.booking.bookingRepository bookingRepository;

    @Autowired
    private CarpoolListingRepository carpoolingRepository;

    public booking createBooking(User user, CarpoolListing listers) {
        // Check if there are available seats
        if (listers.getCapacity() > 0) {
            booking booking = new booking();
            booking.setUser(user);
            booking.setCarpooling(listers);
            booking.setBookingDate(LocalDateTime.now());
            booking.setStatus(BookingStatus.CONFIRMED);

            // Update the carpooling service's available seats
            listers.setCapacity(listers.getCapacity() - 1);

            bookingRepository.save(booking);
            carpoolingRepository.save(listers);

            return booking;
        } else {
            // Handle case when no seats are available
            return null;
        }
    }

    public List<User> getBookedUsersByCarpoolingId(int carpoolingId) {
        List<booking> bookings = bookingRepository.findByCarpoolingId(carpoolingId);

        return bookings.stream()
                .map(booking::getUser)
                .collect(Collectors.toList());
    }
}
