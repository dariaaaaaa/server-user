package com.projectmanagementsystem.serveruser.repo.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames="username")})
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String passwordHash;
    @NotNull
    private String firstName;

    private String lastName;
    private String bio;

    public User() {
    }

    public User(String username, String email, String passwordHash, String firstName, String lastName, String bio) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
