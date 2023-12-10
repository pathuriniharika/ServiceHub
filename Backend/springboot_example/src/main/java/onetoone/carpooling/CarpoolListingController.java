package onetoone.carpooling;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import onetoone.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to CarPooling")

@RestController

public class CarpoolListingController {

    @Autowired
    CarpoolListingRepository carpoolListingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "Get Person's carpoolisting by listers", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/listers")
    List<CarpoolListing> getAllCarpoolListings() {
        return carpoolListingRepository.findAll();
    }
    @ApiOperation(value = "Get Person's listers by there id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/listers/{id}")
    CarpoolListing getlistersById(@PathVariable int id){
        return carpoolListingRepository.findById(id);
    }

    @GetMapping(path = "/listings/byUser/{userId}")
    List<CarpoolListing> getListingsByUserId(@PathVariable int userId) {
        return carpoolListingRepository.findByUserId(userId);
    }

    @ApiOperation(value = "post a service via listers", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping(path = "/listers")
    String createCarpoolListing(@RequestBody CarpoolRequest carpoolRequest) {
        if (carpoolRequest == null || carpoolRequest.getCarpoolListing() == null)
            return failure;

        // Set the user for the carpoolListing
        User user = new User();
        user.setId(carpoolRequest.getUserId());
        carpoolRequest.getCarpoolListing().setUser(user);

        carpoolListingRepository.save(carpoolRequest.getCarpoolListing());
        return success;
    }
    @ApiOperation(value = "update a service via lister's id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PutMapping("/listers/{id}")
    CarpoolListing updateCarpoolListing(@PathVariable int id, @RequestBody CarpoolListing updatedCarpoolListing) {
        CarpoolListing carpoolListing = carpoolListingRepository.findById(id);
        if (carpoolListing == null)
            return null;
        carpoolListingRepository.save(updatedCarpoolListing);
        return carpoolListingRepository.findById(id);
    }
    @ApiOperation(value = "delete a person via listers id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("listers/{id}")
    String deleteCarpoolListing(@PathVariable int id) {
        carpoolListingRepository.deleteById(id);
        return success;
    }
}
