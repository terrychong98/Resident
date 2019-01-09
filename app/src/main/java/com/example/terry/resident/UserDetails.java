package com.example.terry.resident;

public class UserDetails {
    String name;
    String ic;
    String phone;
    String email;
    String address;

    public UserDetails() {
    }

    public UserDetails(String name, String ic, String phone, String email, String address) {
        this.name = name;
        this.ic = ic;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getIc() {
        return ic;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
