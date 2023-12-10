package onetoone.Users;

import io.swagger.annotations.Api;
import onetoone.HomeService.HomeServiceListing;
import onetoone.HomeService.HomeServiceListingRepository;
import onetoone.HomeService.HomeServiceListingService;
import onetoone.Tutors.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eshanth
 *
 */

@RestController
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    private onetoone.booking.bookingService bookingService;
    @Autowired
    private onetoone.booking.bookingRepository bookingRepository;

    @Autowired
    HomeServiceListingRepository homeServiceListingRepository;

    @Autowired
    private HomeServiceListingService homeServiceListingService;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, TutorRepository tutorRepository, UserService userService) {
        this.userRepository = userRepository;
        this.tutorRepository = tutorRepository;
        this.userService = userService;
    }




    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User getProfileById(@PathVariable int id){
        return userRepository.findById(id);
    }

    @PutMapping("/users/{id}/up/{newPassword}")
    User updatePassword(@PathVariable int id, @PathVariable String newPassword) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }

        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }




    @PutMapping("/users/{id}/ue/Email")

    User updateEmail(@PathVariable int id, @RequestBody String newEmail){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        user.setEmail(newEmail);
        userRepository.save(user);
        return user;
    }


    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request) {
        User user = userRepository.findById(id);

        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Only return the ID in the response body
            return ResponseEntity.ok(Collections.singletonMap("details", user));
        } else {
            // If user not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            // Check if user exists and if password matches
            User user = userRepository.findByEmail(email);

            if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace(); // You can also log it using your preferred logging framework

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }



    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }

//    @PostMapping("/users/{id}/setRating")
//    public ResponseEntity<String> setRating(@PathVariable int id, @RequestParam(required = false) Double rating) {
//        if (rating == null) {
//            // Handle the case where rating is not provided
//            return ResponseEntity.badRequest().body("Rating value is required");
//        }
//
//        User user = userRepository.findById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Set the user's rating
//        user.setRating(rating);
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Rating set successfully for user ID: " + id);
//    }

    @PostMapping("/users/{id}/setRating")
    public ResponseEntity<String> setRating(@PathVariable(required = false) Integer id, @RequestParam double rating) {
        // Check if id is null or not a valid integer
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }

        User user = userRepository.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user's rating
        user.setRating(rating);
        userRepository.save(user);

        return ResponseEntity.ok("Rating set successfully for user ID: " + id);
    }

    @GetMapping(path = "/notify/{userId}")
    public ResponseEntity<String> getNotification(@PathVariable int userId) {
        User user = userRepository.findById(userId);

        if (user != null) {
            String notification = user.getNotification();
            if (notification != null) {
                return ResponseEntity.ok(notification);
            } else {
                return ResponseEntity.ok("No notification available for the user.");
            }
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }

    @DeleteMapping("/deleteByCarpoolingId/{carpoolingId}")
    public String deleteBookingsAndNotifyUsers(@PathVariable int carpoolingId) {
        try {
            // Use the getBookedUsersByCarpoolingId method from UserRepository
            List<User> bookedUsers = bookingService.getBookedUsersByCarpoolingId(carpoolingId);

            // Update the notification for each booked user
            for (User user : bookedUsers) {
                user.setNotification("Sorry, your booking for the carpooling service has been cancelled.");
                userRepository.save(user);
            }

            // Use the deleteByCarpoolingId method from BookingRepository
            bookingRepository.deleteByCarpoolingId(carpoolingId);

            return "Booking records deleted, and notifications sent to users.";
        } catch (Exception e) {
            return "Error deleting bookings and sending notifications: " + e.getMessage();
        }
    }

    @GetMapping("/users/ratings")
    List<User> getAllRatings() {
        return userRepository.findAllByRatingIsNotNull();
    }

    @GetMapping("/users/{id}/rating")
    public ResponseEntity<?> getUserRating(@PathVariable int id) {
        User user = userRepository.findById(id);
        if (user != null) {
            Double rating = user.getRating();
            if (rating != null) {
                return ResponseEntity.ok("User ID: " + id + " has a rating of " + rating);
            } else {
                return ResponseEntity.ok("User ID: " + id + " does not have a rating");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/{userId}/listService")
    String listServiceForUser(@PathVariable int userId, @RequestBody HomeServiceListing service) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return failure;
        }

        if (user.getStatus() == UserStatus.PROVIDER) {
            // Add the service to the user and set the lister
            user.addHomeServiceListing(service);
            userRepository.save(user);
            return success;
        } else {
            return failure;
        }
    }


}
