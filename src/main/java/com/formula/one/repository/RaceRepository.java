package com.formula.one.repository;

import com.formula.one.domain.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RaceRepository extends JpaRepository<Race, UUID> {
}
