package com.example.cursach.services;
import com.example.cursach.models.*;
import com.example.cursach.repositories.AnswerRepository;
import com.example.cursach.repositories.QuestionRepository;
import com.example.cursach.repositories.TestRepository;
import com.example.cursach.repositories.UserRepository;
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
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

//    @Autowired
//    public AnswerService(
//            AnswerRepository answerRepository,
//            QuestionRepository questionRepository,
//            TestRepository testRepository,
//            UserRepository userRepository
//    ) {
//        this.answerRepository = answerRepository;
//        this.questionRepository = questionRepository;
//        this.testRepository = testRepository;
//        this.userRepository = userRepository;
//    }

    public void saveAnswers(List<Long> testIds, List<Long> questionIds, List<Long> userIds, List<Integer> answers) {
        if (testIds.size() != questionIds.size() || testIds.size() != userIds.size() || testIds.size() != answers.size()) {
            throw new IllegalArgumentException("Размеры списков не совпадают");
        }

        for (int i = 0; i < testIds.size(); i++) {
            Long testId = testIds.get(i);
            Long questionId = questionIds.get(i);
            Long userId = userIds.get(i);
            Integer answer = answers.get(i);

            // Получение объектов Test, Question, User из базы данных
            Test test = testRepository.findById(testId).orElseThrow();
            Question question = questionRepository.findById(questionId).orElseThrow();
            User user = userRepository.findById(userId).orElseThrow();

            // Создание и сохранение объекта Answer
            Answer newAnswer = new Answer();
            newAnswer.setTest(test);
            newAnswer.setQuestion(question);
            newAnswer.setUser(user);
            newAnswer.setAnswerNumber(answer);

            answerRepository.save(newAnswer);
        }
    }

    public List<Answer> listAnswers(Long userId, Long testId) {
        if (testId != 0 && userId != 0) return answerRepository.findByUser_IdAndTest_Id(userId, testId);
        return answerRepository.findAll();
    }
    public List<Answer> allAnswers() {
        return answerRepository.findAll();
    }

    public List<Answer> listAnswersByTest(Long id) {
        if (id != 0) return answerRepository.findByTestId(id);
        return answerRepository.findAll();
    }
}