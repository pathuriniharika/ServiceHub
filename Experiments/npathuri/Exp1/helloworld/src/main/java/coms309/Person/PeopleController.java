package coms309.Person;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final Map<String, Person> peopleList = new HashMap<>();

    @PostMapping
    public @ResponseBody String createPerson(@RequestBody Person person) {
        peopleList.put(person.getFirstName(), person);
        return "New person " + person.getFirstName() + " saved";
    }

    @GetMapping("/{firstName}")
    public @ResponseBody Person getPerson(@PathVariable String firstName) {
        return peopleList.get(firstName);
    }
}

