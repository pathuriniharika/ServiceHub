package onetoone.Cleaning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CleaningController {

    @Autowired
    CleaningRepository cleaningRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/cleanings")
    List<Cleaning> getAllCleanings() {
        return cleaningRepository.findAll();
    }

    @GetMapping(path = "/cleanings/{id}")
    Cleaning getCleaningById(@PathVariable int id) {
        return cleaningRepository.findById(id);
    }

    @PostMapping(path = "/cleanings")
    String createCleaning(@RequestBody Cleaning cleaning) {
        if (cleaning == null)
            return failure;
        cleaningRepository.save(cleaning);
        return success;
    }

    @PutMapping(path = "/cleanings/{id}")
    Cleaning updateCleaning(@PathVariable int id, @RequestBody Cleaning request) {
        Cleaning cleaning = cleaningRepository.findById(id);
        if (cleaning == null)
            return null;
        cleaningRepository.save(request);
        return cleaningRepository.findById(id);
    }

    @DeleteMapping(path = "/cleanings/{id}")
    String deleteCleaning(@PathVariable int id) {
        cleaningRepository.deleteById(id);
        return success;
    }
}
