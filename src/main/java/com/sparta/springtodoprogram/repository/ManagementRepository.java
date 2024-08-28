package com.sparta.springtodoprogram.repository;

import com.sparta.springtodoprogram.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<Management, Long> {
}
