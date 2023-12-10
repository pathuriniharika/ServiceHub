package onetoone.Profile;

import onetoone.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Niharika Pathuri
 *
 */

public interface ProfileRepository extends JpaRepository<User, Long> {
    User findById(int id);

    @Transactional
    void deleteById(int id);
}