package com.roi.demos.wfproject.repository;

import com.roi.demos.wfproject.domain.CompanyEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyEventRepository extends ReactiveCrudRepository<CompanyEvent, UUID> {
}
