package onetoone.Cleaning;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Cleaning")
@RestController
public class CleaningController {

    @Autowired
    CleaningRepository cleaningRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "get all listings for cleaning", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/cleanings")
    List<Cleaning> getAllCleanings() {
        return cleaningRepository.findAll();
    }
    @ApiOperation(value = "get listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
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
    @ApiOperation(value = "update listings for cleaning", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})



    @PutMapping(path = "/cleanings/{id}")
    Cleaning updateCleaning(@PathVariable int id, @RequestBody Cleaning request) {
        Cleaning cleaning = cleaningRepository.findById(id);
        if (cleaning == null)
            return null;
        cleaningRepository.save(request);
        return cleaningRepository.findById(id);
    }

    @ApiOperation(value = "delete  Person's  listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})


    @DeleteMapping(path = "/cleanings/{id}")
    String deleteCleaning(@PathVariable int id) {
        cleaningRepository.deleteById(id);
        return success;
    }
}
