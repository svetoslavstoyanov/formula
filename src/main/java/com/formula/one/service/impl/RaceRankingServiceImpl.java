package com.formula.one.service.impl;

import com.formula.one.domain.RaceRanking;
import com.formula.one.repository.RaceRankingRepository;
import com.formula.one.service.RaceRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceRankingServiceImpl implements RaceRankingService {

    @Autowired
    private RaceRankingRepository raceRankingRepository;

    @Override
    public List<RaceRanking> saveRaceRankings(List<RaceRanking> raceRankings) {
        return raceRankingRepository.saveAll(raceRankings);
    }
}
