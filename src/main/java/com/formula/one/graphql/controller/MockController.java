package com.formula.one.graphql.controller;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.service.DriverService;
import com.formula.one.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class MockController {

    @Autowired
    TeamService teamService;

    @Autowired
    DriverService driverService;

    private Team generateTeam(String name) {
        Team team = new Team();
        team.setName(name);

        return team;
    }

    private Driver generateDriver(String firstName, String lastName, Integer age, Integer carNumber) {
        Driver driver = new Driver();

        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        driver.setCarNumber(carNumber);

        return driver;
    }


    @QueryMapping
    public List<Team> mockGenerateTeams() {
        List<Team> mockTeams = new ArrayList<>();

        mockTeams.add(generateTeam("Mercedes"));
        mockTeams.add(generateTeam("Red Bull Racing"));
        mockTeams.add(generateTeam("Ferrari"));
        mockTeams.add(generateTeam("Mclaren"));
        mockTeams.add(generateTeam("Alpine"));
        mockTeams.add(generateTeam("AlphaTauri"));
        mockTeams.add(generateTeam("Aston Martin"));
        mockTeams.add(generateTeam("Williams"));
        mockTeams.add(generateTeam("Alfa Romeo"));
        mockTeams.add(generateTeam("Haas"));

        return teamService._createMultiple(mockTeams);
    }

    @QueryMapping
    public List<Driver> mockGenerateDrivers() {
        List<Driver> mockDrivers = new ArrayList<>();

//        MERCEDES
        mockDrivers.add(generateDriver("Lewis", "Hamilton", 35, 44));
        mockDrivers.add(generateDriver("George", "Russell", 23, 63));
//        RED_BULL
        mockDrivers.add(generateDriver("Max", "Verstappen", 23, 1));
        mockDrivers.add(generateDriver("Sergio", "Perez", 32, 11));
//        Ferrari
        mockDrivers.add(generateDriver("Charles", "Leclerc", 24, 16));
        mockDrivers.add(generateDriver("Carlos", "Sainz", 25, 55));
//        McLaren
        mockDrivers.add(generateDriver("Daniel", "Ricciardo", 33, 3));
        mockDrivers.add(generateDriver("Lando", "Norris", 24, 4));
//        Alpine
        mockDrivers.add(generateDriver("Fernando", "Alonso", 41, 14));
        mockDrivers.add(generateDriver("Esteban", "Ocon", 23, 31));
//        AlphaTauri
        mockDrivers.add(generateDriver("Pierre", "Gasly", 26, 10));
        mockDrivers.add(generateDriver("Yuki", "Tsunoda", 20, 22));
//        Aston Martin
        mockDrivers.add(generateDriver("Sebastian", "Vettel", 35, 5));
        mockDrivers.add(generateDriver("Lance", "Stroll", 25, 18));
//        Williams
        mockDrivers.add(generateDriver("Nicholas", "Latifi", 28, 6));
        mockDrivers.add(generateDriver("Alex", "Albon", 26, 23));
//        Alfa Romeo
        mockDrivers.add(generateDriver("Zhou", "Guanyu", 21, 24));
        mockDrivers.add(generateDriver("Valtteri", "Bottas", 34, 77));
//        Haas
        mockDrivers.add(generateDriver("Kevin", "Magnussen", 32, 20));
        mockDrivers.add(generateDriver("Mick", "Schumacher", 21, 47));

        return driverService._createMultiple(mockDrivers);
    }

    @QueryMapping
    public List<Driver> mockAssignDriverToTeams() {
        List<Team> teams = teamService.fetchAll(PageRequest.of(0, 100)).toList();
        List<Driver> drivers = driverService.fetchAll();
        Map<UUID, List<UUID>> teamDriversMapIds = new HashMap<>();

        teams.forEach((team)->{
            List<UUID> teamDriversIds = new ArrayList<>();
            teamDriversIds.add(drivers.remove(0).getId());
            teamDriversIds.add(drivers.remove(0).getId());

            teamDriversMapIds.put(team.getId(), teamDriversIds);
        });

        return driverService._signWithMultiple(teamDriversMapIds);
    }


}
