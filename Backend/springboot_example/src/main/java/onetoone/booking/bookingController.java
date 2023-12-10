package onetoone.booking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import onetoone.Tutors.Tutor;
import onetoone.Tutors.TutorRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;
import onetoone.carpooling.CarpoolListing;
import onetoone.carpooling.CarpoolListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Booking!!!!")

@RestController
@RequestMapping("/bookings")
@Component("firstBookingController")

public class bookingController {
    private final onetoone.booking.bookingRepository bookingRepository;
    private final onetoone.booking.bookingService bookingService;
    private final UserRepository userRepository;
    private final CarpoolListingRepository carpoolListingRepository;

    private final TutorRepository tutorRepository;

    @Autowired
    public bookingController(onetoone.booking.bookingRepository bookingRepository, onetoone.booking.bookingService bookingService, UserRepository userRepository, CarpoolListingRepository carpoolListingRepository, TutorRepository tutorRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
        this.carpoolListingRepository = carpoolListingRepository;
        this.bookingRepository = bookingRepository;
        this.tutorRepository = tutorRepository;
    }

    @ApiOperation(value = "list Person's carpool listings", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/add")
    public ResponseEntity<booking> createBooking(@RequestParam int userId, @RequestParam int carpoolingId) {
        User user = userRepository.findById(userId);
        CarpoolListing carpooling = carpoolListingRepository.findById(carpoolingId);

        if (user != null && carpooling != null) {
            booking booking = bookingService.createBooking(user, carpooling);
            if (booking != null) {
                return new ResponseEntity<>(booking, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // No seats available
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or carpooling not found
        }
    }

    @PostMapping("/bookForTutoring")
    public ResponseEntity<booking> bookForTutoring(@RequestParam int userId, @RequestParam int tutoringId) {
        User user = userRepository.findById(userId);
        Tutor tutor = tutorRepository.findById(tutoringId);

        if (user != null && tutor != null) {
            // Create a new booking instance and set the User and Tutor
            booking newBooking = new booking();
            newBooking.setUser(user);
            newBooking.setTutor(tutor);

            // Save the booking to the repository
            bookingRepository.save(newBooking);

            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        } else {
            // Handle case when user or tutor not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Get Person's listing by there ID", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/{userId}")
    public ResponseEntity<List<CarpoolListing>> getCarpoolListings(@PathVariable int userId) {
        List<booking> userBookings = bookingRepository.findByUserId(userId);

        if (!userBookings.isEmpty()) {
            List<CarpoolListing> bookedCarpoolListings = userBookings.stream()
                    .map(booking::getCarpooling)
                    .collect(Collectors.toList());

            if (!bookedCarpoolListings.isEmpty()) {
                return new ResponseEntity<>(bookedCarpoolListings, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("book/{bookingId}")
    public ResponseEntity<booking> getBookingById(@PathVariable Long bookingId) {
        Optional<booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            return new ResponseEntity<>(optionalBooking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Booking not found
        }
    }

}



