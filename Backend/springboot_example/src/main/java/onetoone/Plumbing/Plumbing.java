package onetoone.Plumbing;

import javax.persistence.*;

@Entity
public class Plumbing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String qualification;

    // Add any additional attributes specific to plumbing services

    // Constructor
    public Plumbing(String description, String qualification) {
        this.description = description;
        this.qualification = qualification;
    }

    // Default constructor
    public Plumbing() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    // Add any additional methods or relationships if needed
}
