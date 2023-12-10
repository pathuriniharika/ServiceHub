package coms309.people;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import java.util.HashMap;

/**
 * Controller used to showcase Create and Read from a LIST
 *
 * @author Niharika Pathuri
 */

// Controller to manage people
@RestController
public class PeopleController {

    // Constructor for PeopleController
    public PeopleController() {
        // Initialize the peopleList with some data
        peopleList.put("1", new Person("Neelima", "Pathuri", "4802588829", "1", "neelimapathuri@gmail.com"));
        peopleList.put("2", new Person("Niharika", "Pathuri", "5156120165", "2", "npathuri@iastate.edu"));
        peopleList.put("3", new Person("Leha", "Dutta", "5157351672", "3", "lduta@iastate.edu"));
    }

    // Note that there is only ONE instance of PeopleController in 
    // Springboot system.
    HashMap<String, Person> peopleList = new  HashMap<>();

    //CRUDL (create/read/update/delete/list)
    // use POST, GET, PUT, DELETE, GET methods for CRUDL

    // THIS IS THE LIST OPERATION
    // gets all the people in the list and returns it in JSON format
    // This controller takes no input. 
    // Springboot automatically converts the list to JSON format 
    // in this case because of @ResponseBody
    // Note: To LIST, we use the GET method
    @GetMapping("/people")
    public @ResponseBody HashMap<String,Person> getAllPersons() {
        System.out.println("Contents of peopleList: " + peopleList);
        return peopleList;
    }

    // THIS IS THE CREATE OPERATION
    // springboot automatically converts JSON input into a person object and 
    // the method below enters it into the list.
    // It returns a string message in THIS example.
    // in this case because of @ResponseBody
    // Note: To CREATE we use POST method

    @PostMapping("/people/{firstName}")
    public @ResponseBody String createPerson(@PathVariable String firstName, @RequestBody Person person) {
        System.out.println(person);
        person.setFirstName(firstName); // Make sure the userId is set from the path variable
        peopleList.put(firstName, person);
        return "New person "+ person.getFirstName() + " Saved";
    }

    // THIS IS THE READ OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We extract the person from the HashMap.
    // springboot automatically converts Person to JSON format when we return it
    // in this case because of @ResponseBody
    // Note: To READ we use GET method

    @GetMapping("/people/{userId}")
    public @ResponseBody Person getPerson(@PathVariable String userId) {
        System.out.println("Received GET request for userId: " + userId);
        Person p = peopleList.get(userId);
        System.out.println("Retrieved person: " + p);
        return p;
    }

    // THIS IS THE UPDATE OPERATION
    // We extract the person from the HashMap and modify it.
    // Springboot automatically converts the Person to JSON format
    // Springboot gets the PATHVARIABLE from the URL
    // Here we are returning what we sent to the method
    // in this case because of @ResponseBody
    // Note: To UPDATE we use PUT method


    @PutMapping("/people/{firstName}")
    public @ResponseBody Person updatePersonByFirstName(@PathVariable String firstName, @RequestBody Person p) {
        System.out.println("Received PUT request for firstName: " + firstName);

        for (Map.Entry<String, Person> entry : peopleList.entrySet()) {
            Person person = entry.getValue();
            if (person.getFirstName().equals(firstName)) {
                person.setUserId(firstName); // Make sure the userId is set from the path variable
                peopleList.replace(entry.getKey(), p);
                return peopleList.get(entry.getKey());
            }
        }

        return null; // Return null if no person with matching firstName is found
    }


    // THIS IS THE DELETE OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We return the entire list -- converted to JSON
    // in this case because of @ResponseBody
    // Note: To DELETE we use delete method

    @DeleteMapping("/people/{firstName}")
    public @ResponseBody HashMap<String, Person> deletePersonByFirstName(@PathVariable String firstName) {
        System.out.println("Received DELETE request for firstName: " + firstName);

        // Create a list to store the keys (userIds) of people to be removed
        List<String> keysToRemove = new ArrayList<>();

        // Iterate through the peopleList
        for (Map.Entry<String, Person> entry : peopleList.entrySet()) {
            Person person = entry.getValue();
            if (person.getFirstName().equals(firstName)) {
                keysToRemove.add(entry.getKey());
            }
        }

        // Remove the people with matching firstName
        for (String key : keysToRemove) {
            peopleList.remove(key);
        }

        return peopleList;
    }
}

