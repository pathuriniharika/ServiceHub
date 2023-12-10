package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Hello World Controller to display the string returned
 *
 * @author Niharika Pathuri
 */

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome To The FlexOffice";
    }
}
