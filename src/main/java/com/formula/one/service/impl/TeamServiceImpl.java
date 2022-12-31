package com.formula.one.service.impl;

import com.formula.one.domain.Driver;
import com.formula.one.domain.Team;
import com.formula.one.repository.DriverRepository;
import com.formula.one.repository.TeamRepository;
import com.formula.one.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//  TODO: ADD ERRORS
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Page<Team> fetchAll(PageRequest pageRequest) {
        return teamRepository.findAll(pageRequest);
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
    public List<Team> _createMultiple(List<Team> teams) {
        return StreamSupport.stream(teamRepository.saveAll(teams).spliterator(), false).collect(Collectors.toList());
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
