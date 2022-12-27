package com.formula.one.service;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    List<Team> fetchAll();

    Team fetchById(UUID teamId);

    Team create(Team team);

    Team edit(Team team);

    void deleteById(UUID teamId);

    List<Driver> addDriver(Driver driver);

    void removeDriverById(UUID teamId, UUID driverId);
}
