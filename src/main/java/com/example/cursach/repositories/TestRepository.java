package com.example.cursach.repositories;

import com.example.cursach.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTitle(String title);

    Test findByIdAndTitle(Long id, String title);
}
