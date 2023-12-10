package onetoone.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("firstBookingRepository")
public interface bookingRepository extends JpaRepository<booking, Long> {

    @Query("SELECT b FROM booking b WHERE b.lister.id = :carpoolingId")
    List<booking> findByCarpoolingId(@Param("carpoolingId") int carpoolingId);


    public List<booking> findByUserId(int Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM booking b WHERE b.lister.id = :carpoolingId")
    void deleteByCarpoolingId(int carpoolingId);
}
