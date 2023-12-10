package onetoone.Cleaning;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CleaningRepository extends JpaRepository<Cleaning, Integer> {
    Cleaning findById(int id);
    void deleteById(int id);

    // New method to get all cleanings
    List<Cleaning> findAll();
}
