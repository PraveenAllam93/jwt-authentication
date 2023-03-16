package com.example.air_quality_check.message.request;

import java.util.Set;

/* Signup form request body */
public class SignUpForm {
   
    private String firstname;
    
   
    private String lastname;

    
    private String username;

    
    private String email;
    
    private Set<String> role;
    
  
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
    	return this.role;
    }
    
    public void setRole(Set<String> role) {
    	this.role = role;
    }
}