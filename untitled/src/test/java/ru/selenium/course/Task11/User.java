package ru.selenium.course.Task11;

public class User {

    public String firstName;
    public String lastName;
    public String address1;
    public String email;
    public String password;
    public String phone;
    public String postcode;
    public String city;

    public User(String firstName, String lastName, String address1, String email, String password,
                String phone, String postcode, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.postcode = postcode;
        this.city = city;
    }

}