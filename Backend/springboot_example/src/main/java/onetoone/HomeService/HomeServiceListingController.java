package onetoone.HomeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Home Service Listings")
@RestController
public class HomeServiceListingController {

    @Autowired
    HomeServiceListingRepository homeServiceListingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @ApiOperation(value = "get all listings for Home Service Listing ", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/listings")
    List<HomeServiceListing> getAllListings() {
        return homeServiceListingRepository.findAll();
    }

    @ApiOperation(value = "get listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/listings/{id}")
    HomeServiceListing getListingById(@PathVariable int id) {
        return homeServiceListingRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/listingsByLister/{listerId}")
    List<HomeServiceListing> getListingsByListerId(@PathVariable int listerId) {
        return homeServiceListingRepository.findByListerId(listerId);
    }


//    @PostMapping(path = "/listings")
//    String createListing(@RequestBody HomeServiceListing homeServiceListing) {
//        if (homeServiceListing == null || homeServiceListing.getTimeslot() == null)
//            return failure;
//
//        homeServiceListingRepository.save(homeServiceListing);
//        return success;
//    }

    @PostMapping(path = "/listings")
    String createListing(@RequestBody HomeServiceListing homeServiceListing) {
        if (homeServiceListing == null)
            return failure;

        homeServiceListingRepository.save(homeServiceListing);
        return success;
    }




    @ApiOperation(value = "update listings for cleaning", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping(path = "/listings/{id}")
    HomeServiceListing updateListing(@PathVariable int id, @RequestBody HomeServiceListing request) {
        HomeServiceListing homeServiceListing = homeServiceListingRepository.findById(id).orElse(null);
        if (homeServiceListing == null)
            return null;
        homeServiceListing.setServiceType(request.getServiceType());
        homeServiceListing.setDescription(request.getDescription());
        homeServiceListing.setPrice(request.getPrice());
        homeServiceListingRepository.save(homeServiceListing);
        return homeServiceListing;
    }

    @ApiOperation(value = "delete  Person's  listings by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping(path = "/listings/{id}")
    String deleteListing(@PathVariable int id) {
        homeServiceListingRepository.deleteById(id);
        return success;
    }
}