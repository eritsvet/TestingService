package com.example.cursach.controllers;

import com.example.cursach.models.*;
import com.example.cursach.repositories.ResultRepository;
import com.example.cursach.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ResultService resultService;

    @GetMapping("/result/{userId}/{testId}")
    public String results(@PathVariable Long userId, @PathVariable Long testId, Model model, Principal principal) {
        Test test = testService.getTestById(testId);
        model.addAttribute("questions", questionService.listQuestions(test.getTitle()));
        model.addAttribute("test", test);
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        model.addAttribute("answers", answerService.listAnswers(userId, testId));
        return "result";
    }

    @PostMapping("/result/save")
    public String saveResult(
            @RequestParam("testId") Long testId,
            @RequestParam("userId") Long userId,
            @RequestParam("userScore") Integer userScore
    ) throws IOException {
        resultService.saveResult(testId, userId, userScore);
        return "redirect:/test";
    }

    @GetMapping("/my-results/{id}")
    public String showResults( Principal principal, User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("results", resultService.listResults(user.getId()));
        return "my-results";
    }
}
