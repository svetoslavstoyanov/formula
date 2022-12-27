package com.formula.one.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Entity
@Table(name = "race_rankings")
public class RaceRanking {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
//    @JsonBackReference
    private Race race;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
//    @JsonBackReference
    private Driver driver;

    @Range(min = 1, max = 100)
    @Column(name = "position", nullable = true)
    private int position;

    @Range(min = 0, max = 100)
    @Column(name = "points_count", nullable = false)
    private int pointsCount;

    @Column(name = "has_fastest_lap", nullable = false)
    private boolean hasFastestLap;

    @Column(name = "did_not_finished", nullable = false)
    private boolean didNotFinished;

    @Column(name = "is_race_winner", nullable = false)
    private boolean isRaceWinner;

    public RaceRanking(UUID id, Race race, Driver driver, int position, int pointsCount, boolean hasFastestLap, boolean didNotFinished, boolean isRaceWinner) {
        this.id = id;
        this.race = race;
        this.driver = driver;
        this.position = position;
        this.pointsCount = pointsCount;
        this.hasFastestLap = hasFastestLap;
        this.didNotFinished = didNotFinished;
        this.isRaceWinner = isRaceWinner;
    }

    public RaceRanking() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public boolean isHasFastestLap() {
        return hasFastestLap;
    }

    public void setHasFastestLap(boolean hasFastestLap) {
        this.hasFastestLap = hasFastestLap;
    }

    public boolean isDidNotFinished() {
        return didNotFinished;
    }

    public void setDidNotFinished(boolean didNotFinished) {
        this.didNotFinished = didNotFinished;
    }

    public boolean isRaceWinner() {
        return isRaceWinner;
    }

    public void setRaceWinner(boolean raceWinner) {
        isRaceWinner = raceWinner;
    }
}
