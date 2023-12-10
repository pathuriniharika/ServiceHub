package onetoone.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    @Query("SELECT AVG(r.ratings) FROM Ratings r WHERE r.carpool.id = :carpoolId")
    Double getAverageRatingByCarpoolId(@Param("carpoolId") Integer carpoolId);

}
