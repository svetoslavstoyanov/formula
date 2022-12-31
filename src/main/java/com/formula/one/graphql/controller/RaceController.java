package com.formula.one.graphql.controller;

import com.formula.one.domain.Race;
import com.formula.one.enums.RaceStatus;
import com.formula.one.graphql.input.CreateRaceInput;
import com.formula.one.graphql.input.EditRaceInput;
import com.formula.one.graphql.input.RaceRankingInput;
import com.formula.one.service.RaceService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class RaceController {

    @Autowired
    private RaceService raceService;

    @QueryMapping
    public List<Race> fetchAllRaces() {
        return raceService.getAll();
    }

    @QueryMapping
    public Race fetchRaceById(@Argument UUID id) {
        return raceService.fetchById(id);
    }

    @MutationMapping
    public Race createRace(@Argument @Valid CreateRaceInput createRaceInput) {
        Race newRace = new Race();

        newRace.setCircuitName(createRaceInput.getCircuitName());
        newRace.setStartDate(LocalDateTime.parse(createRaceInput.getStartDate()));
        newRace.setAverageLapTimeInSeconds(createRaceInput.getAverageLapTimeInSeconds());
        newRace.setLapsCount(createRaceInput.getLapsCount());
        newRace.setLapsCount(createRaceInput.getLapsCount());
        newRace.setStatus(RaceStatus.READY);

        return raceService.create(newRace);
    }

    @MutationMapping
    public Race editRace(@Argument @Valid EditRaceInput editRaceInput) {
        Race race = raceService.fetchById(editRaceInput.getId());

        race.setCircuitName(editRaceInput.getCircuitName());
        race.setStartDate(LocalDateTime.parse(editRaceInput.getStartDate()));
        race.setAverageLapTimeInSeconds(editRaceInput.getAverageLapTimeInSeconds());
        race.setLapsCount(editRaceInput.getLapsCount());
        race.setLapsCount(editRaceInput.getLapsCount());

        return raceService.edit(race);
    }

    @MutationMapping
    public Race startRace(@Argument UUID raceId) {
        return raceService.startRace(raceId);
    }


    @MutationMapping
    public Race finishRace(@Argument UUID raceId, @Argument @Valid @NotNull List<RaceRankingInput> raceRankingsInputs) {
        return raceService.finishRace(raceId, raceRankingsInputs);
    }

    @MutationMapping
    public Race cancelRace(@Argument UUID raceId) {
        return raceService.cancelRace(raceId);
    }
}
