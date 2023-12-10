package onetoone.Painting;

import javax.persistence.*;

import onetoone.Users.User;

@Entity
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String description;

    // Add a one-to-one relationship with User if needed
    // Example: @OneToOne(mappedBy = "painting")
    // private User user;

    public Painting(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Painting() {
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Add getters and setters for User if needed
}
