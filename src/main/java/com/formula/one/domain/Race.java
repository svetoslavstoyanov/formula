package com.formula.one.domain;

import com.formula.one.enums.RaceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.Instant;
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
    @Length(min = 3, max = 100)
    @Column(name = "circuit_name", nullable = false, unique = true)
    private String circuitName;

    @Range(min = 1)
    @Column(name = "laps_count", nullable = false)
    private int lapsCount;

    @Range(min = 1)
    @Column(name = "average_lap_time_in_seconds", nullable = false)
    private int averageLapTimeInSeconds;

    @Range(min = 1)
    @Column(name = "lap_distance_in_metres")
    private int lapDistanceInMetres;

    @NotNull()
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "race_status")
    private RaceStatus status;

    @OneToMany(mappedBy = "race", targetEntity = RaceRanking.class)
    private List<RaceRanking> raceRankings;

    @CurrentTimestamp(timing = GenerationTiming.INSERT)
    public Instant createdAt;

    public Race(UUID id, String circuitName, int lapsCount, int averageLapTimeInSeconds, int lapDistanceInMetres, LocalDateTime startDate, LocalDateTime endDate, List<RaceRanking> raceRankings, RaceStatus raceStatus, Instant createdAt) {
        this.id = id;
        this.circuitName = circuitName;
        this.lapsCount = lapsCount;
        this.averageLapTimeInSeconds = averageLapTimeInSeconds;
        this.lapDistanceInMetres = lapDistanceInMetres;
        this.startDate = startDate;
        this.endDate = endDate;
        this.raceRankings = raceRankings;
        this.status = raceStatus;
        this.createdAt = createdAt;
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

    public RaceStatus getStatus() {
        return status;
    }

    public void setStatus(RaceStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public int getLapDistanceInMetres() {
        return lapDistanceInMetres;
    }

    public void setLapDistanceInMetres(int lapDistanceInMetres) {
        this.lapDistanceInMetres = lapDistanceInMetres;
    }
}
