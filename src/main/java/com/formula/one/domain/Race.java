package com.formula.one.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "circuit_name", nullable = false)
    private String circuitName;

    @Range(min = 1)
    @Column(name = "laps_count", nullable = false)
    private int lapsCount;

    @Range(min = 1)
    @Column(name = "average_lap_time_in_seconds", nullable = false)
    private int averageLapTimeInSeconds;
    @NotNull()
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull()
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "race", targetEntity = RaceRanking.class)
    private List<RaceRanking> raceRankings;

    public Race(UUID id, String circuitName, int lapsCount, int averageLapTimeInSeconds, LocalDateTime startDate, LocalDateTime endDate, List<RaceRanking> raceRankings) {
        this.id = id;
        this.circuitName = circuitName;
        this.lapsCount = lapsCount;
        this.averageLapTimeInSeconds = averageLapTimeInSeconds;
        this.startDate = startDate;
        this.endDate = endDate;
        this.raceRankings = raceRankings;
    }

    public Race() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public int getLapsCount() {
        return lapsCount;
    }

    public void setLapsCount(int lapsCount) {
        this.lapsCount = lapsCount;
    }

    public int getAverageLapTimeInSeconds() {
        return averageLapTimeInSeconds;
    }

    public void setAverageLapTimeInSeconds(int averageLapTimeInSeconds) {
        this.averageLapTimeInSeconds = averageLapTimeInSeconds;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<RaceRanking> getRaceRankings() {
        return raceRankings;
    }

    public void setRaceRankings(List<RaceRanking> raceRankings) {
        this.raceRankings = raceRankings;
    }
}
