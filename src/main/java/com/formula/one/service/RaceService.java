package com.formula.one.service;

import com.formula.one.domain.Race;
import com.formula.one.domain.RaceRanking;
import com.formula.one.domain.Team;
import com.formula.one.enums.RaceStatus;
import com.formula.one.graphql.input.RaceRankingInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface RaceService {

    public Page<Race> getAll(PageRequest pageRequest);

    public Race fetchById(UUID id);

    public Race create(Race race);

    public List<Race> _createMultiple(List<Race> races);

    public Race edit(Race race);

    public Race startRace(UUID raceId);

    public Race cancelRace(UUID raceId);

    public Race finishRace(UUID raceId, List<RaceRankingInput> raceRankingsInputs);

}
