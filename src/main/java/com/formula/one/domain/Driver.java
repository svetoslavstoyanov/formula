package com.formula.one.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "drivers", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class Driver {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Range(min = 18, max = 100)
    @Column(name = "age", nullable = false)
    private int age;

    @Range(min = 1, max = 99)
    @Column(name = "car_number", nullable = false)
    private int carNumber;

    @Range(min = 0)
    @Column(name = "wins_count", nullable = false)
    private int winsCount;

    @Range(min = 0)
    @Column(name = "points_count", nullable = false)
    private int pointsCount;

    @Range(min = 0)
    @Column(name = "fastest_laps_count", nullable = false)
    private int fastestLapsCount;

    @Range(min = 0)
    @Column(name = "crashes_count", nullable = false)
    private int crashesCount;

    @Range(min = 0)
    @Column(name = "did_not_finished_count", nullable = false)
    private int didNotFinishedCount;

    @OneToMany(mappedBy = "driver", targetEntity = RaceRanking.class)
//    @JsonManagedReference
    private List<RaceRanking> raceRankings;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @CurrentTimestamp(timing = GenerationTiming.INSERT)
    public Instant createdAt;

    public Driver(UUID id, String firstName, String lastname, int age, int carNumber, int winsCount, int pointsCount, int fastestLapsCount, int crashesCount, int didNotFinishedCount, List<RaceRanking> raceRankings, Instant createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
        this.age = age;
        this.carNumber = carNumber;
        this.winsCount = winsCount;
        this.pointsCount = pointsCount;
        this.fastestLapsCount = fastestLapsCount;
        this.crashesCount = crashesCount;
        this.didNotFinishedCount = didNotFinishedCount;
        this.raceRankings = raceRankings;
        this.createdAt = createdAt;
    }

    public Driver() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getWinsCount() {
        return this.raceRankings.stream().filter((ranking) -> ranking.getPosition() == 1).toList().size();
//        return winsCount;
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

    public int getFastestLapsCount() {
        return fastestLapsCount;
    }

    public void setFastestLapsCount(int fastestLapsCount) {
        this.fastestLapsCount = fastestLapsCount;
    }

    public int getCrashesCount() {
        return crashesCount;
    }

    public void setCrashesCount(int crashesCount) {
        this.crashesCount = crashesCount;
    }

    public int getDidNotFinishedCount() {
        return didNotFinishedCount;
    }

    public void setDidNotFinishedCount(int didNotFinishedCount) {
        this.didNotFinishedCount = didNotFinishedCount;
    }

    public List<RaceRanking> getRaceRankings() {
        return raceRankings;
    }

    public void setRaceRankings(List<RaceRanking> raceRankings) {
        this.raceRankings = raceRankings;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
