package com.formula.one.graphql.controller;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.graphql.input.CreateTeamInput;
import com.formula.one.graphql.input.EditTeamInput;
import com.formula.one.graphql.input.SortOrder;
import com.formula.one.graphql.resource.PageResource;
import com.formula.one.service.DriverService;
import com.formula.one.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private DriverService driverService;

    @QueryMapping
    public PageResource<Team> fetchAllTeams(@Argument Integer pageIndex, @Argument Integer pageSize, @Argument List<SortOrder> sort) {

        Integer localPageIndex = 0;
        Integer localPageSize = 10_000;
        List<Sort.Order> localSort = new ArrayList<>();

        if (!Objects.isNull(pageIndex)) {
            localPageIndex = pageIndex;
        }

        if (!Objects.isNull(pageSize)) {
            localPageSize = pageSize;
        }

        if (!Objects.isNull(sort)) {
            sort.forEach((s) -> localSort.add(new Sort.Order(s.getDirection(), s.getField())));
        } else {
            localSort.add(new Sort.Order(Sort.Direction.ASC, "id"));
        }

        PageRequest pageRequest = PageRequest.of(localPageIndex, localPageSize, Sort.by(localSort));
        Page<Team> teamPage = teamService.fetchAll(pageRequest);

        return new PageResource<>(teamPage);
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
    public boolean deleteTeamById(@Argument UUID id) {
        teamService.deleteById(id);

        return true;
    }
}
