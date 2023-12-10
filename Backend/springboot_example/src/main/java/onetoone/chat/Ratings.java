package onetoone.chat;

import lombok.Data;
import onetoone.carpooling.CarpoolListing;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Data
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int ratings;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "carpooling_id")
    private CarpoolListing carpool;

    @Column(name = "avg_rating")  // New column for average rating
    private double avgRating;

    // Constructors

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getCarpoolId() {

            return carpool.getId();
        }


    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarpoolListing getCarpool() {
        return carpool;
    }

    public void setCarpool(CarpoolListing carpool) {
        this.carpool = carpool;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}