package coms309.roundTrip.test.controller;

import coms309.roundTrip.test.model.Servicehub;
import coms309.roundTrip.test.repository.ServicehubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicehubController {
    @Autowired
    ServicehubRepository servicehubRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping("servicehub/all")
    List<Servicehub> GetAllServicehub(){
        return servicehubRepository.findAll();
    }

    @PostMapping("servicehub/post/{email}/{password}")
    Servicehub PostServicehubByPath(@PathVariable String email, @PathVariable String password){
        Servicehub newServicehub = new Servicehub();
        newServicehub.setEmail(email);
        newServicehub.setPassword(password);
        servicehubRepository.save(newServicehub);
        return newServicehub;
    }


    @PostMapping("servicehub/post")
    Servicehub PostServicehubByPath(@RequestBody Servicehub newServicehub){
        servicehubRepository.save(newServicehub);
        return newServicehub;
    }

}
