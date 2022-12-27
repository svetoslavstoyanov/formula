package com.formula.one.service;

import com.formula.one.domain.Driver;

import java.util.List;
import java.util.UUID;

public interface DriverService {

    public List<Driver> fetchAll();

    public Driver fetchById(UUID id);

    public Driver create(Driver driver);

    public Driver edit(Driver driver);

    public Driver signWithTeam(UUID driverId, UUID teamId);

    public Driver resignFromTeam(UUID driverId);

    public void deleteById(UUID id);
}
