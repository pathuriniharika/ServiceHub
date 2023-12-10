package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Niharika Pathuri
 */

public class Person {

    private String userId;
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;


    public Person(){
        
    }

    public Person(String firstName, String lastName, String phoneNumber, String userId, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




    @Override
    public String toString() {
        return  "User ID: " + userId + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email: " + email + "\n";
    }
}
