package onetoone.Plumbing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlumbingRepository extends JpaRepository<Plumbing, Integer> {
    Plumbing findById(int id);

    @Transactional
    void deleteById(int id);
}
