package com.formula.one.service;

import com.formula.one.domain.Driver;
import com.formula.one.domain.RaceRanking;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DriverService {

    public List<Driver> fetchAll();

    public List<Driver> fetchDriversByIds(List<UUID> ids);

    public Driver fetchById(UUID id);

    public Driver create(Driver driver);

    public List<Driver> _createMultiple(List<Driver> drivers);

    public Driver edit(Driver driver);

    public List<Driver> updateDrivers(List<Driver> drivers);

    public Driver signWithTeam(UUID driverId, UUID teamId);

    public List<Driver> _signWithMultiple(Map<UUID, List<UUID>> teamDriversMapIds);

    public Driver resignFromTeam(UUID driverId);

    public void deleteById(UUID id);

//    public List<Driver> updateDriversRaceRankings(List<RaceRanking> raceRankings);
}
