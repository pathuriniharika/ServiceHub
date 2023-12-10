//controller receives a POST request at the "/login" endpoint, authenticates the user using loginService.validateUser,
// constructs a JSON response, and returns it to the client. The response contains information about whether
// the authentication was successful and the user's ID.

package onetoone.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "Login")
@RestController
@AllArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully authenticated user"),
            @ApiResponse(code = 400, message = "Bad request, invalid login information"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(
            @ApiParam(value = "Login request object containing username and password", required = true)
            @RequestBody LoginRequest loginRequest
    ) {
        Long userId = loginService.validateUser(loginRequest);

        // create a Map object with a key-value pairs representing the result and user_id
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userId != -1L);
        resultMap.put("user_id", userId);

        // create JSON response
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // return response with 200 status code
        return ResponseEntity.ok(jsonPayload);
    }
}