package com.example.permissionRoleProject.model;

import com.example.permissionRoleProject.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="location")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id",unique = true)
    private Long id;
    @Column(name = "lat")
    private double lat;

    @Column(name="lng")
    private double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Location(){}
}
