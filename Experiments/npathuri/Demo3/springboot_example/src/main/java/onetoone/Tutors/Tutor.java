package onetoone.Tutors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import onetoone.Users.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Tutor entity representing a tutor with additional attributes.
 *
 * Note: This example includes a one-to-one relationship with a User entity.
 *
 * @author Eshanth reddy
 */
@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String description;
    private String qualification;

    private String fullname;
    @OneToOne(mappedBy = "tutors", cascade = CascadeType.ALL)
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
    public String getfullname() {
        return fullname;
    }

    public void setfullname(String fullname) {
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

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
