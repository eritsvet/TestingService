package com.example.cursach.repositories;

import com.example.cursach.models.Question;
import com.example.cursach.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTitle(String title);

    List<Question> findByTestId(Long id);
}
