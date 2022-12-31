package com.formula.one.graphql.input;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

public class RaceRankingInput {

    @NotNull
    private UUID driverId;

    @Range(min = 1, max = 20)
    private int position;

    @Range(min = 1, max = 26)
    private int pointsCount;

    @NotNull
    private boolean hasFastestLap;

    @NotNull
    private boolean didNotFinished;


    @NotNull
    private boolean didCrashed;

    public RaceRankingInput(UUID driverId, int position, int pointsCount, boolean hasFastestLap, boolean didNotFinished,boolean didCrashed) {
        this.driverId = driverId;
        this.position = position;
        this.pointsCount = pointsCount;
        this.hasFastestLap = hasFastestLap;
        this.didNotFinished = didNotFinished;
        this.didCrashed = didCrashed;
    }

    public RaceRankingInput() {
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
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

    public boolean hasFastestLap() {
        return hasFastestLap;
    }

    public void setHasFastestLap(boolean hasFastestLap) {
        this.hasFastestLap = hasFastestLap;
    }

    public boolean didNotFinished() {
        return didNotFinished;
    }

    public void setDidNotFinished(boolean didNotFinished) {
        this.didNotFinished = didNotFinished;
    }

    public boolean didCrashed() {
        return didCrashed;
    }

    public void setDidCrashed(boolean didCrashed) {
        this.didCrashed = didCrashed;
    }
}
