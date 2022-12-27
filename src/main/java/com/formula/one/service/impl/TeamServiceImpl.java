package com.formula.one.service.impl;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.repository.DriverRepository;
import com.formula.one.repository.TeamRepository;
import com.formula.one.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//  TODO: ADD ERRORS
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Team> fetchAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team fetchById(UUID teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            return null;
        }
        return team.get();
    }

    @Override
    public Team create(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team edit(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(UUID teamId) {
        teamRepository.deleteById(teamId);
    }
}