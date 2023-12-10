package onetoone.registration;

import onetoone.appuser.AppUser;
import onetoone.appuser.AppUserRole;
import onetoone.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public String register(RegistrationRequest request) {
        appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserService.AppUserRole.USER
                )
        );

        return "{'email':"+request.getEmail()+"}";
    }
}