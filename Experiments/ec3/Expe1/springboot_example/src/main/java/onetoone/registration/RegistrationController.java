package onetoone.registration;

import onetoone.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Registration")
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    private final AppUserService appUserService;

    @ApiOperation(value = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User registered successfully")
    })
    @PostMapping
    public String register(
            @ApiParam(value = "Registration request object containing user details", required = true)
            @RequestBody RegistrationRequest request
    ) {
        return registrationService.register(request);
    }

    @ApiOperation(value = "Update user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile updated successfully")
    })
    @PutMapping("/{email}")
    public ResponseEntity<String> updateProfile(
            @ApiParam(value = "User email address", required = true)
            @PathVariable String email,
            @ApiParam(value = "Update profile request object containing new user details", required = true)
            @RequestBody UpdateProfileRequest request
    ) {
        appUserService.updateUserProfile(
                email,
                request.getFirstName(),
                request.getLastName()
        );
        return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
    }
}
