package coms309.user;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;

/**
 * Controller used to showcase Create and Read from a LIST
 *
 * @author Eshanth
 */

@RestController
public class UserController {

    // Note that there is only ONE instance of UserController in the Spring Boot system.
    HashMap<Integer, User> userList = new HashMap<>();

    // LIST OPERATION - Get all users in JSON format
    @GetMapping("/users")
    public @ResponseBody HashMap<Integer, User> getAllUsers() {
        return userList;
    }

    // CREATE OPERATION - Create a new user
    @PostMapping("/users")
    public @ResponseBody String createUser(@RequestBody User user) {
        // Assuming userId is generated elsewhere
        userList.put(user.getUserId(), user);
        return "New user with ID " + user.getUserId() + " created";
    }

    // READ OPERATION - Get user by user ID
    @GetMapping("/users/{userId}")
    public @ResponseBody User getUser(@PathVariable int userId) {
        return userList.get(userId);
    }

    // UPDATE OPERATION - Update user by user ID
    @PutMapping("/users/{userId}")
    public @ResponseBody User updateUser(@PathVariable int userId, @RequestBody User user) {
        userList.replace(userId, user);
        return userList.get(userId);
    }

    // DELETE OPERATION - Delete user by user ID
    @DeleteMapping("/users/{userId}")
    public @ResponseBody HashMap<Integer, User> deleteUser(@PathVariable int userId) {
        userList.remove(userId);
        return userList;
    }
}