package com.example.cursach.services;
import com.example.cursach.models.*;
import com.example.cursach.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResultService {
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    public void saveResult(Long testId, Long userId, Integer userScore) {
            Test test = testRepository.findById(testId).orElseThrow();
            User user = userRepository.findById(userId).orElseThrow();


            Result newResult = new Result();
            newResult.setTest(test);
            newResult.setUser(user);
            newResult.setUserScore(userScore);

            resultRepository.save(newResult);
    }
    public List<Result> listResults(Long userId) {
        if (userId != 0) return resultRepository.findByUserId(userId);
        return resultRepository.findAll();
    }

    public List<Result> listResultsByTest(Long testId) {
        if (testId != 0) return resultRepository.findByTestId(testId);
        return resultRepository.findAll();
    }
}
