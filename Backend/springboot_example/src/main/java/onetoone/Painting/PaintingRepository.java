package onetoone.Painting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
    Painting findById(int id);
    void deleteById(int id);
    List<Painting> findAll();

}
