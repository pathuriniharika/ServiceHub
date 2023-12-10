package onetoone.Tutors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 /**
 * Repository interface for Tutor entity.
 *
 * Note: You can add custom query methods here if needed.
 *
 * @author Eshanth reddy
 */
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    // You can add custom query methods here if needed
    Tutor findById(int id);

    @Transactional
    void deleteById(int id);

}