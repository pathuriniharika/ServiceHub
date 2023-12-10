package com.example.servicehub;

public class Tutor {
    private int id;
    private String name;
    private String qualifications;
    private String description;
    private String phone;
    private String email;

    // Getter Methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setter Methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
