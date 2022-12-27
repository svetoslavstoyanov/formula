package com.formula.one.graphql.controller;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.graphql.input.CreateTeamInput;
import com.formula.one.graphql.input.EditTeamInput;
import com.formula.one.service.DriverService;
import com.formula.one.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private DriverService driverService;

    @QueryMapping
    public List<Team> fetchAllTeams() {
        return teamService.fetchAll();
    }

    @QueryMapping
    public Team fetchTeamById(@Argument UUID id) {
        return teamService.fetchById(id);
    }

    @MutationMapping
    public Team createTeam(@Argument @Valid CreateTeamInput newTeam) {
        Team team = new Team();
        team.setName(newTeam.getName());

        return teamService.create(team);
    }

    @MutationMapping
    public Team editTeam(@Argument @Valid EditTeamInput editedTeam) {
        Team updatedTeam = new Team();
        updatedTeam.setId(editedTeam.getId());
        updatedTeam.setName(editedTeam.getName());

        return teamService.edit(updatedTeam);
    }

    @MutationMapping
    public List<Driver> addDriver(@Argument UUID teamId, @Argument UUID driverId) {
        driverService.signWithTeam(driverId, teamId);

        Team team = teamService.fetchById(teamId);

        return team.getDrivers();
    }

    @MutationMapping
    public List<Driver> removeDriverById(@Argument UUID teamId, @Argument UUID driverId) {
        driverService.resignFromTeam(driverId);

        Team team = teamService.fetchById(teamId);

        return team.getDrivers();
    }

    @MutationMapping
    public boolean deleteTeamById(@Argument UUID id){
        teamService.deleteById(id);

        return true;
    }
}
