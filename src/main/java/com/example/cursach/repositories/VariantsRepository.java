package com.example.cursach.repositories;

import com.example.cursach.models.Question;
import com.example.cursach.models.Test;
import com.example.cursach.models.Variants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariantsRepository extends JpaRepository<Variants, Long> {
    List<Variants> findByQuestionId(Long id);

}
