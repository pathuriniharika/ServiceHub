package onetoone;

import onetoone.Tutors.TutorRepository;
import onetoone.Users.UserRepository;
import onetoone.carpooling.CarpoolListingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Eshanth reddy
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create users and tutors with associated data
    @Bean
    CommandLineRunner initUserData(
            UserRepository userRepository,
            TutorRepository tutorRepository, CarpoolListingRepository carpoolListingRepository) {
        return args -> {
//
//            CarpoolListing lister1 = new CarpoolListing("Des Moines", "Your Destination", 4, "Monday");
//            CarpoolListing lister2 = new CarpoolListing("Des Mois", "Your Denation", 20, "Monday");
//            CarpoolListing lister3 = new CarpoolListing("Moines", " Destination", 4, "Monday");
//
//            User user1 = new User("J3333", "john@somemail.com", "ytf", "bd");
//            User user2 = new User("Jane", "jane@somemail.com", "sdfs", "sfdf");
//            User user3 = new User("Justin", "justin@somemail.com", "sdfd", "dfds");
//
//
//            Tutor tutor1 = new Tutor("Math","Passionate about teaching math", "Ph.D. in Mathematics","Eshanth");
//            Tutor tutor2 = new Tutor("Science", "Specialized in physics and chemistry", "M.Sc. in Science Education","Rishitha");
//            Tutor tutor3 = new Tutor("physics", "Specialized in physics and chemistry", "M.Sc. in Science Education","Mamatha");
//
//
////
//            tutor1.addUsers(userRepository.findById(1));
//            tutor1.addUsers(userRepository.findById(2));
//            tutor1.addUsers(userRepository.findById(3));
//
//
//            lister1.addUsers(userRepository.findById(1));
//            lister1.addUsers(userRepository.findById(2));
//            lister1.addUsers(userRepository.findById(3));
//
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
//            userRepository.save(user4);

//            tutorRepository.save(tutor1);
//            tutorRepository.save(tutor2);
//            tutorRepository.save(tutor3);

//            carpoolListingRepository.save(lister1);
//            carpoolListingRepository.save(lister2);
//            carpoolListingRepository.save(lister3);
//


        };
    }
}