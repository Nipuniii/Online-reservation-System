package com.abcrestaurant.restaurantweb.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER; // Default value is USER

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = true) // Nullable foreign key
    private Branch branch;

    // No-argument constructor
    public User() {
    }

    // Constructor with all arguments
    public User(String name, String email, String password, UserRole role, Branch branch) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.branch = branch;
    }

    // Getters and setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
