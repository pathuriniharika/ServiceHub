package onetoone.HomeService;

import onetoone.Users.User;
import onetoone.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class HomeServiceListingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeServiceListingRepository homeServiceListingRepository;

    public void setListerForServiceListing(int userId, String serviceType, String description, double price, LocalDateTime timeSlot) {
        // Fetch the user from the UserRepository
        User user = userRepository.findById(userId);

        if (user != null) {
            // Create a new HomeServiceListing
            HomeServiceListing serviceListing = new HomeServiceListing(serviceType, description, price);
//            serviceListing.setTimeslot(timeSlot);

            // Set the fetched user as the lister for the service listing
            serviceListing.setLister(user);

            // Add the service listing to the user's list of home service listings
            List<HomeServiceListing> userHomeServiceListings = user.getHomeServiceListings();
            userHomeServiceListings.add(serviceListing);
            user.setHomeServiceListings(userHomeServiceListings);

            // Save the changes to persist the association
            userRepository.save(user);
        } else {
            // Handle the case where the user with the provided userId doesn't exist
            System.out.println("User not found!");
            // You can throw an exception or handle it according to your application's logic
        }
    }

}
