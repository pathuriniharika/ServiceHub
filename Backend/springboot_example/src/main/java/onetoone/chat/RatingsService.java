package onetoone.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public Double getAverageRatingByCarpoolId(Integer carpoolId) {
        return ratingsRepository.getAverageRatingByCarpoolId(carpoolId);
    }

    public void addRatingAndUpdateAverage(Ratings newRating) {
        // Calculate the average rating for the corresponding carpool listing
        Integer carpoolId = newRating.getCarpool().getId();
        Double averageRating = getAverageRatingByCarpoolId(carpoolId);

        // Update the avgRating in the Ratings entity
        newRating.setAvgRating(averageRating);

        // Save the new rating with the updated average rating
        ratingsRepository.save(newRating);
    }
}
