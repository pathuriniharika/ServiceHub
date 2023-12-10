package onetoone.chat;

import onetoone.carpooling.CarpoolListing;
import onetoone.carpooling.CarpoolListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Controller
@ServerEndpoint(value = "/ratings/{carpool_id}")
public class RatingsSocket {

    private static RatingsRepository ratingsRepo;
    private static CarpoolListingRepository carpoolListingRepository; // Add this repository

    @Autowired
    public void setRatingsRepository(RatingsRepository repo) {
        ratingsRepo = repo;
    }

    @Autowired
    public void setCarpoolListingRepository(CarpoolListingRepository repo) {
        carpoolListingRepository = repo;
    }

    private static Map<Session, Long> sessionCarpoolIdMap = new Hashtable<>();
    private static Map<Long, Session> carpoolIdSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(RatingsSocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("carpool_id") Long carpoolId) throws IOException {
        logger.info("Entered into Open");
        sessionCarpoolIdMap.put(session, carpoolId);
        carpoolIdSessionMap.put(carpoolId, session);

    }@OnMessage
    public void onMessage(Session session, String message) {
        try {
            String[] parts = message.split(":");
            if (parts.length >= 2) {
                String action = parts[0].trim();
                int carpoolId;

                try {
                    carpoolId = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    sendToSession(session, "Error: Invalid carpoolId format. It should be a valid number.");
                    return;
                }

                if ("SUBMIT_RATING".equals(action) && parts.length == 4) {
                    int rating;
                    String description = parts[3].trim();

                    try {
                        rating = Integer.parseInt(parts[2].trim());
                    } catch (NumberFormatException e) {
                        sendToSession(session, "Error: Invalid rating format. It should be a number.");
                        return;
                    }

                    saveRatingAndDescription(session, carpoolId, rating, description);
                } else if ("GET_AVERAGE_RATING".equals(action)) {
                    sendAverageRatingForCarpoolListing(session, carpoolId);
                } else {
                    sendToSession(session, "Error: Invalid message format. Expected format 'SUBMIT_RATING:carpoolId:rating:review' or 'GET_AVERAGE_RATING:carpoolId'.");
                }
            } else {
                sendToSession(session, "Error: Invalid message format.");
            }
        } catch (Exception e) {
            sendToSession(session, "Error: An unexpected error occurred.");
            logger.error("An unexpected error occurred", e);
        }
    }

    private void saveRatingAndDescription(Session session, int carpoolId, int rating, String description) {
        CarpoolListing carpool = carpoolListingRepository.findById(carpoolId);

        if (carpool == null) {
            sendToSession(session, "Error: CarpoolListing not found for Carpool ID: " + carpoolId);
            return;
        }

        try {
            Ratings ratingEntry = new Ratings();
            ratingEntry.setCarpool(carpool);
            ratingEntry.setRatings(rating);
            ratingEntry.setDescription(description);

            ratingsRepo.save(ratingEntry);

            // After saving the new rating, update the average rating for the corresponding carpool listing
            Double averageRating = ratingsRepo.getAverageRatingByCarpoolId(carpoolId);

            // Update the avgRating in the Ratings entity
            ratingEntry.setAvgRating(averageRating);
            ratingsRepo.save(ratingEntry);

            // Update the avgRating in the corresponding CarpoolListing entity (if needed)
            carpool.setAvgRating(averageRating);
            carpoolListingRepository.save(carpool);

            sendToSession(session, "Success: Rating and review submitted for Carpool ID: " + carpoolId);
        } catch (Exception e) {
            sendToSession(session, "Error: Unable to save the rating and review. Please try again.");
            logger.error("Unable to save the rating and review", e);
        }
    }

    private void sendAverageRatingForCarpoolListing(Session session, int carpoolId) {
        Double averageRating = ratingsRepo.getAverageRatingByCarpoolId(carpoolId);

        if (averageRating != null) {
            sendToSession(session, "Average Rating for Carpool ID " + carpoolId + ": " + averageRating);
        } else {
            sendToSession(session, "No ratings found for Carpool ID " + carpoolId);
        }
    }


    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("Failed to send message to session", e);
        }
    }
}