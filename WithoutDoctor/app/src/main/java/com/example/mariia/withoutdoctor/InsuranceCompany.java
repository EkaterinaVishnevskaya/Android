package com.example.mariia.withoutdoctor;

/**
 * Created by ekaterina on 30.11.17.
 */

public class InsuranceCompany {
    private String email;
    private String name;

    public InsuranceCompany(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String ID) {
        this.email = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InsuranceCompany() {
    }
}
