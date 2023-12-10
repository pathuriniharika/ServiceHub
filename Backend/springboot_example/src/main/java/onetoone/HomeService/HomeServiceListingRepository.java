package onetoone.HomeService;;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import onetoone.Users.User;

@Repository
public interface HomeServiceListingRepository extends JpaRepository<HomeServiceListing, Integer> {
    List<HomeServiceListing> findAll();

    // Add a method to find listings by lister (user)
    List<HomeServiceListing> findByLister(User user);

    List<HomeServiceListing> findByListerId(int listerId);


    List<HomeServiceListing> findByUser(User user);


    @Modifying
    @Query("UPDATE HomeServiceListing h SET h.serviceType = :serviceType, h.description = :description, h.price = :price WHERE h.lister.id = :listerId")
    void updateByListerId(@Param("listerId") int listerId, @Param("serviceType") String serviceType, @Param("description") String description, @Param("price") double price);



}


