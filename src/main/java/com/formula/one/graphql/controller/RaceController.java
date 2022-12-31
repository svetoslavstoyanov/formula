package com.formula.one.graphql.controller;

import com.formula.one.domain.Race;
import com.formula.one.domain.Team;
import com.formula.one.enums.RaceStatus;
import com.formula.one.graphql.input.CreateRaceInput;
import com.formula.one.graphql.input.EditRaceInput;
import com.formula.one.graphql.input.RaceRankingInput;
import com.formula.one.graphql.input.SortOrder;
import com.formula.one.graphql.resource.PageResource;
import com.formula.one.service.RaceService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class RaceController {

    @Autowired
    private RaceService raceService;

    @QueryMapping
    public PageResource<Race> fetchAllRaces(@Argument Integer pageIndex, @Argument Integer pageSize, @Argument List<SortOrder> sort) {

        Integer localPageIndex = 0;
        Integer localPageSize = 10_000;
        List<Sort.Order> localSort = new ArrayList<>();

        if (!Objects.isNull(pageIndex)) {
            localPageIndex = pageIndex;
        }

        if (!Objects.isNull(pageSize)) {
            localPageSize = pageSize;
        }

        if (!Objects.isNull(sort)) {
            sort.forEach((s) -> localSort.add(new Sort.Order(s.getDirection(), s.getField())));
        } else {
            localSort.add(new Sort.Order(Sort.Direction.ASC, "id"));
        }

            PageRequest pageRequest = PageRequest.of(localPageIndex, localPageSize, Sort.by(localSort));
        Page<Race> racesPage =  raceService.getAll(pageRequest);

        return new PageResource<Race>(racesPage);
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
        newRace.setLapDistanceInMetres(createRaceInput.getLapDistanceInMetres());
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
        race.setLapDistanceInMetres(editRaceInput.getLapDistanceInMetres());

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
