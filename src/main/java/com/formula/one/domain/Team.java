package com.formula.one.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "team", targetEntity = Driver.class)
//    @JsonManagedReference
    private List<Driver> drivers;

    @Range(min = 0)
    @Column(name = "wins_count", nullable = false)
    private int winsCount;

    @Range(min = 0)
    @Column(name = "points_count", nullable = false)
    private int pointsCount;

    public Team(UUID id, String name, List<Driver> drivers, int winsCount, int pointsCount) {
        this.id = id;
        this.name = name;
        this.drivers = drivers;
        this.winsCount = winsCount;
        this.pointsCount = pointsCount;
    }

    public Team() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public void setWinsCount(int winsCount) {
        this.winsCount = winsCount;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }
}
