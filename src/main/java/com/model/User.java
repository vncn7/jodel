package com.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Tuser")
public class User implements Serializable {

    private static final long serialVersionUID = 1L; // Default serial version UID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 64, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 64)
    private String password;

    // Default no-args constructor for JPA
    public User() {
    }

    // Constructor with parameters (id, username, password)
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}