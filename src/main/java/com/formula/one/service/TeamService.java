package com.formula.one.service;

import com.formula.one.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    Page<Team> fetchAll(PageRequest pageRequest);

    Team fetchById(UUID teamId);

    Team create(Team team);

    List<Team> _createMultiple(List<Team> teams);

    Team edit(Team team);

    void deleteById(UUID teamId);
}
