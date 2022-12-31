package com.formula.one.service.impl;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.repository.DriverRepository;
import com.formula.one.service.DriverService;
import com.formula.one.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

//  TODO: ADD ERRORS
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TeamService teamService;

    @Override
    public List<Driver> fetchAll() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> fetchDriversByIds(List<UUID> ids) {
        return driverRepository.findAllById(ids);
    }

    @Override
    public Driver fetchById(UUID id) {
        return driverRepository.findById(id).get();
    }

    @Override
    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> _createMultiple(List<Driver> drivers) {
        return driverRepository.saveAll(drivers);
    }

    @Override
    public Driver edit(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> updateDrivers(List<Driver> drivers) {
        return driverRepository.saveAll(drivers);
    }

    @Override
    public Driver signWithTeam(UUID driverId, UUID teamId) {
        Driver driver = fetchById(driverId);
        Team team = teamService.fetchById(teamId);
        driver.setTeam(team);

        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> _signWithMultiple(Map<UUID, List<UUID>> teamDriversMapIds) {
        List<Driver> drivers = driverRepository.findAll();
        Map<UUID, Team> teamsMap = teamService.fetchAll(PageRequest.of(0, 100)).stream().collect(Collectors.toMap(Team::getId, Function.identity()));

        List<Driver> updatedDrivers = new ArrayList<>();

        for (UUID teamId : teamDriversMapIds.keySet()) {
            for (Driver driver : drivers) {
                if (teamDriversMapIds.get(teamId).contains(driver.getId())) {
                    driver.setTeam(teamsMap.get(teamId));
                    updatedDrivers.add(driver);
                }
            }
        }

        return driverRepository.saveAll(updatedDrivers);
    }

    @Override
    public Driver resignFromTeam(UUID driverId) {
        Driver driver = fetchById(driverId);
        driver.setTeam(null);

        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(UUID id) {
        driverRepository.deleteById(id);
    }
}
