package onetoone.Tutors;

import java.util.List;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        List<Tutor> tutors = tutorRepository.findAll();

        return tutors;
    }

    @GetMapping(path = "/tutors/{id}")
    Tutor getTutorById(@PathVariable int id){
        return tutorRepository.findById(id);
    }

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
