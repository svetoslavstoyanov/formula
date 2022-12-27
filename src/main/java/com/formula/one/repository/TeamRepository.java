package com.formula.one.repository;

import com.formula.one.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
