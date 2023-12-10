package onetoone.carpooling;

import onetoone.chat.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class carpoolingService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public Double getAverageRatingByCarpoolId(Integer carpoolId) {
        return ratingsRepository.getAverageRatingByCarpoolId(carpoolId);
    }
}
