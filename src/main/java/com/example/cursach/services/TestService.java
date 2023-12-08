package com.example.cursach.services;

import com.example.cursach.models.*;
import com.example.cursach.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final VariantsRepository variantsRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ResultRepository resultRepository;
    public List<Test> listTests(String title) {
        if (title != null) return testRepository.findByTitle(title);
        return testRepository.findAll();
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public Test getTestById(Long id){
        return testRepository.findById(id).orElseThrow();
    }


    public void saveTest(Test test) throws IOException {
        testRepository.save(test);
    }

    @Transactional
    public void deleteTest(Long id) {
        Test test = testRepository.findById(id).orElse(null);
        List<Answer> answers = answerRepository.findByTestId(id);
        answerRepository.deleteAll(answers);
        List<Result> results = resultRepository.findByTestId(id);
        resultRepository.deleteAll(results);
        if (test != null) {
            List<Question> questions = questionRepository.findByTestId(id);
            for (Question question : questions) {
                List<Variants> variants = variantsRepository.findByQuestionId(question.getId());
                variantsRepository.deleteAll(variants);
            }
            questionRepository.deleteAll(questions);
            testRepository.delete(test);
        }
    }

    public void updateTest(Long id, Test test) throws IOException {
        Test existingTest = testRepository.findById(id).orElse(null);

        if (existingTest != null) {
            existingTest.setTitle(test.getTitle());
            existingTest.setDescription(test.getDescription());
            existingTest.setPoints(test.getPoints());
            testRepository.save(existingTest);
        }
    }
}
