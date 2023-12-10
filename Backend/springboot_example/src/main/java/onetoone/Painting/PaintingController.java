package onetoone.Painting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to painting")
@RestController
public class PaintingController {

    @Autowired
    PaintingRepository paintingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "get listings for this service", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/paintings")
    List<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }
    @ApiOperation(value = "get listing by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/paintings/{id}")
    Painting getPaintingById(@PathVariable int id) {
        return paintingRepository.findById(id);
    }
    @ApiOperation(value = "post a listing by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping(path = "/paintings")
    String createPainting(@RequestBody Painting painting) {
        if (painting == null)
            return failure;
        paintingRepository.save(painting);
        return success;
    }
    @ApiOperation(value = "update a listing by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping(path = "/paintings/{id}")
    Painting updatePainting(@PathVariable int id, @RequestBody Painting request) {
        Painting painting = paintingRepository.findById(id);
        if (painting == null)
            return null;
        paintingRepository.save(request);
        return paintingRepository.findById(id);
    }
    @ApiOperation(value = "delete a listing by id", response = EmbeddedLdapProperties.Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping(path = "/paintings/{id}")
    String deletePainting(@PathVariable int id) {
        paintingRepository.deleteById(id);
        return success;
    }


}
