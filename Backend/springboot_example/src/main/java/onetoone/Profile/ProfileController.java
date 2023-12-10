package onetoone.Profile;

import io.swagger.annotations.Api;
import onetoone.Users.User;
import onetoone.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author Niharika Pathuri
 *
 */

//get request
//put request if password change
//put request email
//frontend
//replace mobile number with email


@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to profile")
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/{id}")
    User getProfileById(@PathVariable int id){
        return userRepository.findById(id);
    }

    @PutMapping("profiles/{id}/up/password")
    User updatePassword(@PathVariable int id, @RequestBody String newPassword){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }

    @PutMapping("/{id}/ue/email")
    User updateEmail(@PathVariable int id, @RequestBody String newEmail){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        user.setEmail(newEmail);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    String deleteProfile(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
}
