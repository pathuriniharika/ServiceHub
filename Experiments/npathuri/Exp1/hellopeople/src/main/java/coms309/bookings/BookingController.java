package coms309.bookings;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@RestController
public class BookingController {
    HashMap<String, Booking> bookingsList = new HashMap<>();

    @GetMapping("/bookings")
    public @ResponseBody HashMap<String, Booking> getAllBookings() {
        return bookingsList;
    }

    @PostMapping("/bookings/{bookingId}")
    public @ResponseBody String createBooking(@PathVariable String bookingId, @RequestBody Booking booking) {
        booking.setBookingId(bookingId);
        bookingsList.put(bookingId, booking);
        return "New booking "+ booking.getBookingId() + " Saved";
    }

    @GetMapping("/bookings/{bookingId}")
    public @ResponseBody Booking getBooking(@PathVariable String bookingId) {
        return bookingsList.get(bookingId);
    }

    @PutMapping("/bookings/{bookingId}")
    public @ResponseBody Booking updateBooking(@PathVariable String bookingId, @RequestBody Booking booking) {
        if (bookingsList.containsKey(bookingId)) {
            booking.setBookingId(bookingId);
            bookingsList.put(bookingId, booking);
            return booking;
        }
        return null;
    }

    @DeleteMapping("/bookings/{bookingId}")
    public @ResponseBody HashMap<String, Booking> deleteBooking(@PathVariable String bookingId) {
        bookingsList.remove(bookingId);
        return bookingsList;
    }


}





