package com.example.finalasignment;

public class User {
    private long userId;
    private String username;
    private String icNumber;
    private String dob;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String username, String icNumber, String dob, String email, String phoneNumber, String password) {
        this.username = username;
        this.icNumber = icNumber;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Getter methods
    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getICNumber() {
        return icNumber;
    }

    public String getDOB() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods (if needed)

    // Custom getter
    public String getFormattedPhoneNumber() {
        return "+60 " + phoneNumber;
    }

    // Custom setter
    public void setEncryptedPassword(String encryptedPassword) {
        // Perform encryption logic if needed
        this.password = encryptedPassword;
    }
}
