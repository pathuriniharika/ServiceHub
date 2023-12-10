package onetoone.Cleaning;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningRepository extends JpaRepository<Cleaning, Integer> {
    Cleaning findById(int id);
    void deleteById(int id);
}
