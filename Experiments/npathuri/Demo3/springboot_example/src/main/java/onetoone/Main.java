package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import onetoone.Laptops.Laptop;
//import onetoone.Laptops.LaptopRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

import onetoone.Tutors.Tutor;
import onetoone.Tutors.TutorRepository;

/**
 *
 * @author Niharika Pathuri
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
            TutorRepository tutorRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com", "ytf", "bd");
            User user2 = new User("Jane", "jane@somemail.com", "sdfs", "sfdf");
            User user3 = new User("Justin", "justin@somemail.com", "sdfd", "dfds");

            Tutor tutor1 = new Tutor("Math", "Passionate about teaching math", "Ph.D. in Mathematics","Eshanth");
            Tutor tutor2 = new Tutor("Science", "Specialized in physics and chemistry", "M.Sc. in Science Education","Rishitha");
            Tutor tutor3 = new Tutor("physics", "Specialized in physics and chemistry", "M.Sc. in Science Education","Mamatha");

            user1.setTutor(tutor1);
            user2.setTutor(tutor2);
            user3.setTutor(tutor3);

            tutor1.setUser(user1);
            tutor2.setUser(user2);
            tutor3.setUser(user3);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            tutorRepository.save(tutor1);
            tutorRepository.save(tutor2);
            tutorRepository.save(tutor3);
        };
    }
}