package onetoone.Painting;

import javax.persistence.*;

import onetoone.Users.User;

@Entity
@Table(name = "painting")
public class Painting {

    // Add a one-to-one relationship with User if needed
    // Example: @OneToOne(mappedBy = "painting")
    // private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String description;

    private double price;


    public Painting(String type, String description) {
        this.type = type;
        this.description = description;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
