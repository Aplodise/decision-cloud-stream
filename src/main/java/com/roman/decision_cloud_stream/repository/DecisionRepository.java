package com.roman.decision_cloud_stream.repository;

import com.roman.decision_cloud_stream.domain.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<Decision, Long> {
}
