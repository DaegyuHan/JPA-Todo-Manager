package com.sparta.springtodoprogram.config.management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<Management, Long> {
}
