package com.example.cursach.services;

import com.example.cursach.models.*;
import com.example.cursach.repositories.QuestionRepository;
import com.example.cursach.repositories.TestRepository;
import com.example.cursach.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.ArrayList;
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

    public List<Question> listQuestions(String title) {
        if (title != null) return questionRepository.findByTitle(title);
        return questionRepository.findAll();
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void createQuestions(String title, List<String> descriptions, List<Integer> points, List<Integer> answers) {
        for (int i = 0; i < descriptions.size(); i++) {
            String description = descriptions.get(i);
            Integer testPoints = points.get(i);
            Integer answer = answers.get(i);
            List<Test> tests = testRepository.findByTitle(title);
            Question newQuestion = new Question();
            newQuestion.setTest(tests.get(0));
            newQuestion.setTitle(title);
            newQuestion.setDescription(description);
            newQuestion.setPoints(testPoints);
            newQuestion.setAnswer(answer);

            questionRepository.save(newQuestion);
        }
    }

    public List<Question> listQuestionsByTest(Long id) {
        if (id != null) return questionRepository.findByTestId(id);
        return questionRepository.findAll();
    }

    public void updateQuestions(List <Long> questionIds, String title, List<String> descriptions, List<Integer> points, List<Integer> answers) {
        for (int i = 0; i < questionIds.size(); i++) {
            Question existingQuestion = questionRepository.findById(questionIds.get(i)).orElse(null);
            String description = descriptions.get(i);
            Integer testPoints = points.get(i);
            Integer answer = answers.get(i);
            if (existingQuestion != null) {

                existingQuestion.setTitle(title);
                existingQuestion.setDescription(description);
                existingQuestion.setPoints(testPoints);
                existingQuestion.setAnswer(answer);

                questionRepository.save(existingQuestion);
            }
        }
    }
}