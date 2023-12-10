package onetoone.Cleaning;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cleaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serviceType;
    private String description;

    // Add a no-arg constructor
    public Cleaning() {
    }

    // Constructors, getters, and setters (similar to the Tutor entity)

    // Example constructor:
    public Cleaning(String serviceType, String description) {
        this.serviceType = serviceType;
        this.description = description;
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
