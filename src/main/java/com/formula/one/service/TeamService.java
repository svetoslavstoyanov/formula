package com.formula.one.service;

import com.formula.one.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TeamService {

    Page<Team> fetchAll(PageRequest pageRequest);

    Team fetchById(UUID teamId);

    Team create(Team team);

    Team edit(Team team);

    void deleteById(UUID teamId);
}
