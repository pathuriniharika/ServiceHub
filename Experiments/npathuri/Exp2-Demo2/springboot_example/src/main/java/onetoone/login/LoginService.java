//It receives a LoginRequest object which contains an email and a password.
//
//It attempts to find a user with the given email and password using the appUserRepository.findAppUserByEmailAndPassword() method.
//
//If a user is found, it retrieves the user's ID and returns it. If not, it returns -1L.
package onetoone.login;

import onetoone.appuser.AppUser;
import onetoone.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AppUserRepository appUserRepository;

    public Long validateUser(LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());

        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Long userId = appUserOptional.map(AppUser::getUserId).orElse(-1L);

        System.out.println(userId != -1L); // User not found or invalid credentials
        return userId;
    }
}
