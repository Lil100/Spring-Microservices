package com.schoolmanagement.configuration.streams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamsRepository extends JpaRepository<StreamsEntity, Long> {
}
