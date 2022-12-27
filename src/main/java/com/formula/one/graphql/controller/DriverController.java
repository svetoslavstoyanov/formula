package com.formula.one.graphql.controller;

import com.formula.one.domain.Driver;
import com.formula.one.graphql.input.CreateDriverInput;
import com.formula.one.graphql.input.EditDriverPersonalInfoInput;
import com.formula.one.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @QueryMapping
    public List<Driver> fetchAllDrivers() {
        return driverService.fetchAll();
    }

    @QueryMapping
    public Driver fetchDriverById(@Argument UUID id) {
        return driverService.fetchById(id);
    }

    @MutationMapping
    public Driver createDriver(@Argument @Valid CreateDriverInput driverInput) {
        Driver newDriver = new Driver();
        newDriver.setFirstName(driverInput.getFirstName());
        newDriver.setLastName(driverInput.getLastName());
        newDriver.setAge(driverInput.getAge());
        newDriver.setCarNumber(driverInput.getCarNumber());

        return driverService.create(newDriver);
    }

    @MutationMapping
    public Driver editDriverPersonalInfo(@Argument @Valid EditDriverPersonalInfoInput driverInput) {
        Driver editedDriver = driverService.fetchById(driverInput.getId());

        if (Objects.nonNull(driverInput.getFirstName())) {
            editedDriver.setFirstName(driverInput.getFirstName());
        }
        if (Objects.nonNull(driverInput.getLastName())) {
            editedDriver.setLastName(driverInput.getLastName());

        }
        if (Objects.nonNull(driverInput.getCarNumber())) {
            editedDriver.setCarNumber(driverInput.getCarNumber());
        }
        if (Objects.nonNull(driverInput.getAge())) {
            editedDriver.setAge(driverInput.getAge());
        }

        return driverService.edit(editedDriver);
    }

    @MutationMapping
    public boolean deleteDriverById(@Argument UUID id) {
        driverService.deleteById(id);

        return true;
    }

//    TODO: add method for updating race stats

}
