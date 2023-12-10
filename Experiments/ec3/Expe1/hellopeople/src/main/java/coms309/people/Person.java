package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Eshanth
 */

public class Person {

    private String fullName;

    private String email;

    private String address;

    private String phone;

    public Person(){
        
    }

    public Person(String fullName, String email, String address, String phone){
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getfullName() {
        return this.fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getemail() {
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getphone() {
        return this.phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return fullName + " "
               + email + " "
               + address + " "
               + phone;
    }
}
