package com.formula.one.graphql.controller;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Race;
import com.formula.one.domain.RaceRanking;
import com.formula.one.domain.Team;
import com.formula.one.enums.RaceStatus;
import com.formula.one.service.DriverService;
import com.formula.one.service.RaceRankingService;
import com.formula.one.service.RaceService;
import com.formula.one.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Controller
public class MockController {

    @Autowired
    TeamService teamService;

    @Autowired
    DriverService driverService;

    @Autowired
    RaceService raceService;

    @Autowired
    RaceRankingService raceRankingService;

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

    private Race generateRace(String circuitName, int lapTimeInSeconds, int lapsCount, int lapDistanceInMetres, LocalDateTime startDate) {
        Race race = new Race();

        race.setCircuitName(circuitName);
        race.setStartDate(startDate);
        race.setAverageLapTimeInSeconds(lapTimeInSeconds);
        race.setLapsCount(lapsCount);
        race.setLapDistanceInMetres(lapDistanceInMetres);
        race.setStatus(RaceStatus.READY);

        return race;
    }

    private RaceRanking generateRaceRaking(Race race, Driver driver, int position, boolean didNotFinished, boolean hasFastestLap, int pointsCount) {
        RaceRanking ranking = new RaceRanking();

        ranking.setRace(race);
        ranking.setDriver(driver);
        ranking.setPosition(position);
        ranking.setDidNotFinished(didNotFinished);
        ranking.setHasFastestLap(hasFastestLap);
        ranking.setPointsCount(pointsCount);

        return ranking;
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

        teams.forEach((team) -> {
            List<UUID> teamDriversIds = new ArrayList<>();
            teamDriversIds.add(drivers.remove(0).getId());
            teamDriversIds.add(drivers.remove(0).getId());

            teamDriversMapIds.put(team.getId(), teamDriversIds);
        });

        return driverService._signWithMultiple(teamDriversMapIds);
    }

    @QueryMapping
    public List<Race> mockGenerateRaces() {
        List<Race> races = new ArrayList<>();
        List<LocalDateTime> dates = new ArrayList<>();
        LocalDateTime firstDate = LocalDateTime.now();

        for (int i = 0; i < 5; i++) {
            dates.add(firstDate.plusDays(i * 7));
        }

        races.add(generateRace("BAHRAIN GRAND PRIX", 91, 57, 5412, dates.get(races.size())));
        races.add(generateRace("SAUDI ARABIAN GRAND PRIX", 90, 50, 6174, dates.get(races.size())));
        races.add(generateRace("AUSTRALIAN GRAND PRIX", 80, 58, 5278, dates.get(races.size())));
        races.add(generateRace("GRAN PREMIO D’ITALIA", 81, 53, 5793, dates.get(races.size())));
        races.add(generateRace("GRAND PRIX DE MONACO", 72, 78, 3337, dates.get(races.size())));

        return raceService._createMultiple(races);
    }

    @QueryMapping
    public Race mockSimulateRace() {
        List<Sort.Order> sortByDate = new ArrayList<>();
        sortByDate.add(new Sort.Order(Sort.Direction.ASC, "startDate"));

        Page<Race> racesPage = raceService.getAll(PageRequest.of(0, 100, Sort.by(sortByDate)));
        List<Driver> drivers = driverService.fetchAll();
        List<Integer> points = new ArrayList<>(Stream.of(25, 18, 15, 12, 10, 8, 6, 4, 2, 1).toList());

        Race race = racesPage.stream().filter((filterRace) -> filterRace.getStatus() == RaceStatus.READY).findFirst().orElse(null);

        if (Objects.isNull(race)) {
            return null;
        }

        Collections.shuffle(drivers);

        AtomicInteger raceRankingPosition = new AtomicInteger(0);
        AtomicInteger countDidNotFinished = new AtomicInteger(new Random().nextInt(4));
        int fastestLapDriverPosition = new Random().nextInt(10);

        List<RaceRanking> mockRaceRankings = drivers.stream().map((driver) -> {

            raceRankingPosition.addAndGet(1);
            AtomicInteger pointsCount = new AtomicInteger(0);
            boolean didNotFinished = false;
            boolean hasFastestLap = false;

            if (raceRankingPosition.get() <= 10) {
                pointsCount.addAndGet(points.remove(0));
            } else {
                if (countDidNotFinished.get() > 0 && raceRankingPosition.get() >= drivers.size() - countDidNotFinished.get()) {
                    countDidNotFinished.decrementAndGet();
                    didNotFinished = new Random().nextBoolean();
                }
            }


            if (raceRankingPosition.get() == fastestLapDriverPosition) {
                pointsCount.addAndGet(1);
                hasFastestLap = true;
            }


            return generateRaceRaking(race, driver, raceRankingPosition.get(), didNotFinished, hasFastestLap, pointsCount.get());
        }).toList();

        List<RaceRanking> raceRankings = raceRankingService.saveRaceRankings(mockRaceRankings);

        race.setEndDate(race.getStartDate().plusSeconds((long) race.getLapsCount() * race.getAverageLapTimeInSeconds()));
        race.setStatus(RaceStatus.FINISHED);
        race.setRaceRankings(raceRankings);

        return raceService.edit(race);
    }

}
