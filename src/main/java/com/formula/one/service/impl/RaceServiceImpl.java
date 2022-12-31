package com.formula.one.service.impl;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Race;
import com.formula.one.domain.RaceRanking;
import com.formula.one.enums.RaceStatus;
import com.formula.one.graphql.input.RaceRankingInput;
import com.formula.one.repository.RaceRepository;
import com.formula.one.service.DriverService;
import com.formula.one.service.RaceRankingService;
import com.formula.one.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RaceRankingService raceRankingService;

    @Autowired
    private DriverService driverService;

    @Override
    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    @Override
    public Race fetchById(UUID id) {
        return raceRepository.findById(id).get();
    }

    @Override
    public Race create(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public Race edit(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public Race startRace(UUID raceId) {
        Race race = raceRepository.findById(raceId).get();
        race.setStatus(RaceStatus.RUNNING);

        return raceRepository.save(race);
    }

    @Override
    public Race cancelRace(UUID raceId) {
        Race race = raceRepository.findById(raceId).get();
        race.setStatus(RaceStatus.CANCELLED);

        return raceRepository.save(race);
    }

    @Override
    public Race finishRace(UUID raceId, List<RaceRankingInput> raceRankingsInputs) {
        Race race = this.fetchById(raceId);

        Map<UUID, RaceRankingInput> raceRankingInputMap = raceRankingsInputs
                .stream()
                .collect(Collectors.toMap(RaceRankingInput::getDriverId, Function.identity()));

        Map<UUID, Driver> driversMap = driverService
                .fetchDriversByIds(
                        raceRankingsInputs
                                .stream()
                                .map(RaceRankingInput::getDriverId).toList())
                .stream().map(driver -> {
                    driver.setPointsCount(driver.getPointsCount() + raceRankingInputMap.get(driver.getId()).getPointsCount());

                    if (raceRankingInputMap.get(driver.getId()).getPosition() == 1) {
                        driver.setWinsCount(driver.getWinsCount() + 1);
                    }
                    if (raceRankingInputMap.get(driver.getId()).hasFastestLap()) {
                        driver.setFastestLapsCount(driver.getFastestLapsCount() + 1);
                    }
                    if (raceRankingInputMap.get(driver.getId()).didNotFinished()) {
                        driver.setDidNotFinishedCount(driver.getDidNotFinishedCount() + 1);
                    }
                    if (raceRankingInputMap.get(driver.getId()).didCrashed()) {
                        driver.setCrashesCount(driver.getCrashesCount() + 1);
                    }

                    return driver;
                })
                .collect(Collectors.toMap(Driver::getId, Function.identity()));


        List<RaceRanking> rankingsToSave = raceRankingsInputs.stream().map((input) -> {
            Driver driver = driversMap.get(input.getDriverId());
            RaceRanking ranking = new RaceRanking();

            ranking.setRace(race);
            ranking.setDriver(driver);
            ranking.setPosition(input.getPosition());
            ranking.setDidNotFinished(input.didNotFinished());
            ranking.setHasFastestLap(input.hasFastestLap());
            ranking.setPointsCount(input.getPointsCount());

            return ranking;

        }).toList();

        driverService.updateDrivers(driversMap.values().stream().toList());

        List<RaceRanking> rankings = raceRankingService.saveRaceRankings(rankingsToSave);

        race.setEndDate(LocalDateTime.now());
        race.setStatus(RaceStatus.FINISHED);
        race.setRaceRankings(rankings);

        return raceRepository.save(race);
    }
}
