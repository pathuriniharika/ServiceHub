package onetoone.carpooling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarpoolListingRepository extends JpaRepository<CarpoolListing, Integer> {
    // You can define custom query methods here if needed

    CarpoolListing findById(int id);

    List<CarpoolListing> findByUserId(int userId);

    @Transactional
    void deleteById(int id);



    @Query("SELECT AVG(r.ratings) FROM Ratings r WHERE r.carpool.id = :carpoolId")
    Double getAverageRatingByCarpoolId(@Param("carpoolId") Integer carpoolId);


}