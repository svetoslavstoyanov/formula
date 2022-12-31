package com.formula.one.service;

import com.formula.one.domain.Race;
import com.formula.one.domain.RaceRanking;
import com.formula.one.enums.RaceStatus;
import com.formula.one.graphql.input.RaceRankingInput;

import java.util.List;
import java.util.UUID;

public interface RaceService {

    public List<Race> getAll();

    public Race fetchById(UUID id);

    public Race create(Race race);

    public Race edit(Race race);

    public Race startRace(UUID raceId);

    public Race cancelRace(UUID raceId);

    public Race finishRace(UUID raceId, List<RaceRankingInput> raceRankingsInputs);

}
