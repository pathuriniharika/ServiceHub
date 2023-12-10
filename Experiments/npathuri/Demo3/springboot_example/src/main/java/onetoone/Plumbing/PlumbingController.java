package onetoone.Plumbing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlumbingController {

    @Autowired
    PlumbingRepository plumbingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/plumbing")
    List<Plumbing> getAllPlumbingServices() {
        return plumbingRepository.findAll();
    }

    @GetMapping(path = "/plumbing/{id}")
    Plumbing getPlumbingServiceById(@PathVariable int id){
        return plumbingRepository.findById(id);
    }

    @PostMapping(path = "/plumbing")
    String createPlumbingService(@RequestBody Plumbing plumbing){
        if (plumbing == null)
            return failure;
        plumbingRepository.save(plumbing);
        return success;
    }

    @PutMapping(path = "/plumbing/{id}")
    Plumbing updatePlumbingService(@PathVariable int id, @RequestBody Plumbing request){
        Plumbing plumbing = plumbingRepository.findById(id);
        if(plumbing == null)
            return null;
        plumbingRepository.save(request);
        return plumbingRepository.findById(id);
    }

    @DeleteMapping(path = "/plumbing/{id}")
    String deletePlumbingService(@PathVariable int id){
        plumbingRepository.deleteById(id);
        return success;
    }
}
