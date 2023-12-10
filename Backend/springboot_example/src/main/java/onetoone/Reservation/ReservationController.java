package onetoone.Reservation;

import io.swagger.annotations.Api;
import onetoone.HomeService.HomeServiceListing;
import onetoone.HomeService.HomeServiceListingRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Defines the REST endpoints for managing bookings.
//        Provides methods to get all bookings, get a booking by ID, create a new booking, update an existing booking, and delete a booking.
@RestController
@Component("secondBookingController")
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private HomeServiceListingRepository homeServiceListingRepository;

    @Autowired
    private UserRepository userRepository;
    //getallbookingsbyprovider

    // Retrieve a booking by its unique ID
    @GetMapping(path = "/Bookings/{id}")
    Reservation getBookingById(@PathVariable int id){
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
        return reservation;
    }

    // Add a new method to retrieve bookings by service type
    @GetMapping(path = "/Bookings/type/{serviceType}")
    List<Reservation> getBookingsByType(@PathVariable String serviceType){
        return reservationRepository.findByServiceType(serviceType);
    }


    // Create a new booking
    @PostMapping(path = "/Bookings")
    public String createBooking(@RequestBody Reservation reservation) {
        if (reservation != null && reservation.getServiceType() != null && reservation.getTimeslot() != null && reservation.getUser() != null) {
            User user = reservation.getUser();
            User lister = reservation.getLister();
            // Fetch service details based on reservation.getServiceId()

            if (user != null && lister != null /* && serviceDetails != null */) {
                // Check service availability and conflicts
                List<Reservation> existingReservations = reservationRepository.findByServiceTypeAndTimeslot(
                        reservation.getServiceType(),
                        reservation.getTimeslot()
                );

                if (existingReservations.isEmpty()) {
                    // Create a new reservation
                    Reservation newReservation = new Reservation();
                    newReservation.setServiceType(reservation.getServiceType());
                    newReservation.setTimeslot(reservation.getTimeslot());
                    newReservation.setUser(user);
                    reservation.setLister(lister);
                    // Set service details

                    reservationRepository.save(newReservation);
                    return "Booking created successfully";
                } else {
                    return "Time slot is already booked";
                }
            } else {
                return "User, lister, or service not found";
            }
        } else {
            return "All fields are required or contain invalid data";
        }
    }







    // Method to check if user is present in the listings
    private boolean isUserInListings(User user) {
        List<HomeServiceListing> userListings = homeServiceListingRepository.findByUser(user);
        return !userListings.isEmpty();
    }



    @GetMapping(path = "/Bookings/{id}/user")
    public int getUserIdForBooking(@PathVariable int id){
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }

        User user = reservation.getUser();
        if (user == null) {
            // Handle the case where the user associated with the reservation is null
            // or return an appropriate response
            return -1; // Return a default value or handle null case
        }

        return user.getId(); // Return the ID of the user associated with the reservation
    }






    // Update an existing booking
    @PutMapping(path = "/Bookings/{id}")
    Reservation updateBooking(@PathVariable int id, @RequestBody Reservation request){
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
        reservationRepository.save(request);
        return reservationRepository.findById(id);
    }

    // Delete a booking by its unique ID
    @DeleteMapping(path = "/Bookings/{id}")
    String deleteBooking(@PathVariable int id){
        reservationRepository.deleteById(id);
        return "success";
    }

    @GetMapping(path = "/Bookings/user/{userId}")
    List<Reservation> getBookingsByUserId(@PathVariable int userId){
        return reservationRepository.findByUserId(userId);
    }

    @GetMapping(path = "/Bookings/All")
    List<Reservation> getAllBookings() {
        return reservationRepository.findAll();
    }


}

