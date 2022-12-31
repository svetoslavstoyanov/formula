package com.formula.one.repository;

import com.formula.one.domain.Race;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.UUID;

public interface RaceRepository extends ListPagingAndSortingRepository<Race, UUID>, CrudRepository<Race, UUID> {
}
