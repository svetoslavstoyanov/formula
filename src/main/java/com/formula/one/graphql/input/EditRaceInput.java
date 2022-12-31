package com.formula.one.graphql.input;

import java.time.LocalDateTime;
import java.util.UUID;

public class EditRaceInput extends CreateRaceInput {

    private UUID id;

    public EditRaceInput(String circuitName, int lapsCount, int averageLapTimeInSeconds, String startDate, UUID id, int lapDistanceInMetres) {
        super(circuitName, lapsCount, averageLapTimeInSeconds, startDate, lapDistanceInMetres);
        this.id = id;
    }

    public EditRaceInput() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
