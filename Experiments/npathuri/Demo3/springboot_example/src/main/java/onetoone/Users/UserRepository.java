package onetoone.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Niharika Pathuri
 * 
 */ 

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);

    @Transactional
    void deleteById(int id);

    User findByEmail(String email);
}
