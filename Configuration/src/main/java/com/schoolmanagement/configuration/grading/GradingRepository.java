package com.schoolmanagement.configuration.grading;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradingRepository extends JpaRepository<GradingEntity, Long> {
}

