package onetoone.Plumbing;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to plumbing")
@RestController
public class PlumbingController {

    @Autowired
    PlumbingRepository plumbingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "get all listings", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/plumbing")
    List<Plumbing> getAllPlumbingServices() {
        return plumbingRepository.findAll();
    }
    @ApiOperation(value = "get all listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/plumbing/{id}")
    Plumbing getPlumbingServiceById(@PathVariable int id){
        return plumbingRepository.findById(id);
    }
    @ApiOperation(value = "add new listings", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping(path = "/plumbing")
    String createPlumbingService(@RequestBody Plumbing plumbing){
        if (plumbing == null)
            return failure;
        plumbingRepository.save(plumbing);
        return success;
    }
    @ApiOperation(value = "update  listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping(path = "/plumbing/{id}")
    Plumbing updatePlumbingService(@PathVariable int id, @RequestBody Plumbing request){
        Plumbing plumbing = plumbingRepository.findById(id);
        if(plumbing == null)
            return null;
        plumbingRepository.save(request);
        return plumbingRepository.findById(id);
    }
    @ApiOperation(value = "delete listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping(path = "/plumbing/{id}")
    String deletePlumbingService(@PathVariable int id){
        plumbingRepository.deleteById(id);
        return success;
    }
}
