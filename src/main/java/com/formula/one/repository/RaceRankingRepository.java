package com.formula.one.repository;

import com.formula.one.domain.RaceRanking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RaceRankingRepository extends JpaRepository<RaceRanking, UUID> {
}
