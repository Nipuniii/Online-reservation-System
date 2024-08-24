package com.abcrestaurant.restaurantweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "facilities")
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facilities_name", nullable = false)
    private String facilitiesName;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    // Getters and setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacilitiesName() {
        return facilitiesName;
    }

    public void setFacilitiesName(String facilitiesName) {
        this.facilitiesName = facilitiesName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
