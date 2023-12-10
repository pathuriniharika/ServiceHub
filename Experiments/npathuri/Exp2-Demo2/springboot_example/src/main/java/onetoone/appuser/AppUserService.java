package onetoone.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public Optional<AppUser> getUserByEmailAndPassword(String email, String password) {
        return appUserRepository.findAppUserByEmailAndPassword(email, password);
    }

    public void saveUser(AppUser user) {
        appUserRepository.save(user);
    }

    public void updateUserProfile(String email, String firstName, String lastName) {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(email);
        userOptional.ifPresent(user -> {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            appUserRepository.save(user);
        });
    }

    public void assignUserRole(AppUser user, AppUserRole role) {
        user.setRole(role);
        appUserRepository.save(user);
    }

    // Add more user-related services as needed

    public enum AppUserRole {
        USER, ADMIN; // Add any other roles as needed
    }
}
