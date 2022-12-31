package com.formula.one.graphql.input;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class CreateRaceInput {
    @NotBlank
    @Length(min = 3, max = 20)
    private String circuitName;

    @Range(min = 1)
    private int lapsCount;

    @Range(min = 1)
    private int averageLapTimeInSeconds;
    @NotNull()
    @NotBlank()
    private String startDate;

    @Range(min = 1)
    private int lapDistanceInMetres;


    public CreateRaceInput(String circuitName, int lapsCount, int averageLapTimeInSeconds, String startDate,int lapDistanceInMetres) {
        this.circuitName = circuitName;
        this.lapsCount = lapsCount;
        this.averageLapTimeInSeconds = averageLapTimeInSeconds;
        this.startDate = startDate;
        this.lapDistanceInMetres  = lapDistanceInMetres;
    }

    public CreateRaceInput() {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getLapDistanceInMetres() {
        return lapDistanceInMetres;
    }

    public void setLapDistanceInMetres(int lapDistanceInMetres) {
        this.lapDistanceInMetres = lapDistanceInMetres;
    }
}
