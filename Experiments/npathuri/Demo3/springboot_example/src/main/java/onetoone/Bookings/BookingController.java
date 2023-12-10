package onetoone.Bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Defines the REST endpoints for managing bookings.
//        Provides methods to get all bookings, get a booking by ID, create a new booking, update an existing booking, and delete a booking.
@RestController
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    // Retrieve all bookings
    @GetMapping(path = "/bookings")
    List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Retrieve a booking by its unique ID
    @GetMapping(path = "/bookings/{id}")
    Booking getBookingById(@PathVariable int id){
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
        return booking;
    }

    // Create a new booking
    @PostMapping(path = "/bookings")
    String createBooking(@RequestBody Booking booking){
        if (booking == null ||
                booking.getServiceType() == null ||
                booking.getTutorName() == null ||
                booking.getSubjectName() == null ||
                booking.getTimeslot() == null) {
            return "All fields are required";
        }

        // Check for conflicts
        List<Booking> existingBookings = bookingRepository.findByTutorNameAndTimeslot(
                booking.getTutorName(),
                booking.getTimeslot()
        );

        if (!existingBookings.isEmpty()) {
            return "Time slot is already booked";
        }

        bookingRepository.save(booking);
        return "success";
    }



    // Update an existing booking
    @PutMapping(path = "/bookings/{id}")
    Booking updateBooking(@PathVariable int id, @RequestBody Booking request){
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
        bookingRepository.save(request);
        return bookingRepository.findById(id);
    }

    // Delete a booking by its unique ID
    @DeleteMapping(path = "/bookings/{id}")
    String deleteBooking(@PathVariable int id){
        bookingRepository.deleteById(id);
        return "success";
    }
}

