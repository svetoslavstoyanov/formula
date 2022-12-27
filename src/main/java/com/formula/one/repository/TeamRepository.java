package com.formula.one.repository;

import com.formula.one.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.UUID;

public interface TeamRepository extends ListPagingAndSortingRepository<Team, UUID>, CrudRepository<Team, UUID> {
}
