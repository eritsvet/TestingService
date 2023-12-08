package com.example.cursach.repositories;

import com.example.cursach.models.Answer;
import com.example.cursach.models.Variants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByUser_IdAndTest_Id(Long userId, Long testId);

    List<Answer> findByTestId(Long id);
}