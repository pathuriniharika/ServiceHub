package onetoone.Tutors;

import onetoone.Users.User;

import javax.persistence.*;

@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String description;
    private String qualification;
    private String fullname;

    @ManyToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Tutor table
    private User user;

    public Tutor(String subject, String description, String qualification, String fullname) {
        this.subject = subject;
        this.description = description;
        this.qualification = qualification;
        this.fullname = fullname;
    }

    public Tutor() {
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    // Getter and Setter for the user relationship

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
