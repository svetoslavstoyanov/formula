package com.formula.one.service.impl;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.repository.DriverRepository;
import com.formula.one.repository.TeamRepository;
import com.formula.one.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//  TODO: ADD ERRORS
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Driver> fetchAll() {
        return driverRepository.findAll();
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
    public Driver edit(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver signWithTeam(UUID driverId, UUID teamId) {
        Driver driver = fetchById(driverId);
        Team team = teamRepository.findById(teamId).get();
        driver.setTeam(team);

        return driverRepository.save(driver);
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
