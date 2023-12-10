package onetoone.Tutors;

import java.util.List;
;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestParam;
/**
import java.util.List;

/**
 * Controller class for handling Tutor-related HTTP requests.
 *
 * Note: This is a basic example. You might want to enhance it based on your specific use cases.
 *
 * @author Eshanth reddy
 */


@RestController
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/tutors")
    List<Tutor> getAllTutors() {

        return tutorRepository.findAll();
    }
//    @GetMapping("/tutors")
//    public String searchTutorsByName(@RequestParam(name ="name") String fullname) {
//        // In a real application, you would query your database here
//        // For simplicity, we'll return a placeholder response
//        return "Searching for tutors by name: " + fullname;
//    }
    @GetMapping(path = "/tutors/{id}")
    Tutor getTutorById(@PathVariable int id){
        return tutorRepository.findById(id);
    }
//@GetMapping("/tutors/byName/{fullname}")
//ResponseEntity<?> getTutorByname(@PathVariable String fullname) {
//    try {
//        List<Tutor> tutors = (List<Tutor>) tutorRepository.findByFullname(fullname);
//
//        if (tutors.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
//        } else if (tutors.size() == 1) {
//            // If there's exactly one result, return it
//            Tutor tutor = tutors.get(0);
//            return new ResponseEntity<>(tutor, HttpStatus.OK); // 200 OK
//        } else {
//            // If there are multiple results, you might want to return a list
//            return new ResponseEntity<>(tutors, HttpStatus.OK); // 200 OK
//        }
//    } catch (Exception e) {
//        // Print the exception details to the console
//        System.err.println("An error occurred in getTutorByname");
//        e.printStackTrace();
//
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
//    }
//}


    @PostMapping(path = "/tutors")
    String createTutor(@RequestBody Tutor Tutor){
        if (Tutor == null)
            return failure;
        tutorRepository.save(Tutor);
        return success;
    }

    @PutMapping(path = "/tutors/{id}")
    Tutor updateTutor(@PathVariable int id, @RequestBody Tutor request){
        Tutor tutor = tutorRepository.findById(id);
        if(tutor == null)
            return null;
        tutorRepository.save(request);
        return tutorRepository.findById(id);
    }

    @DeleteMapping(path = "/tutors/{id}")
    String deleteTutor(@PathVariable int id){
        tutorRepository.deleteById(id);
        return success;
    }
}
