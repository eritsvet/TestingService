package com.example.cursach.repositories;

import com.example.cursach.models.Answer;
import com.example.cursach.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserId(Long userId);

    List<Result> findByTestId(Long testId);
}