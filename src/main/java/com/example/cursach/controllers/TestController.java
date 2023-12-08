package com.example.cursach.controllers;

import com.example.cursach.models.Question;
import com.example.cursach.models.Test;
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
public class TestController {
    private final TestService testService;
    private final QuestionService questionService;
    private final VariantsService variantsService;
    private final AnswerService answerService;

    @GetMapping("/")
    public String home( Principal principal, Model model) {
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        return "home";
    }

    @GetMapping("/test")
    public String tests(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("tests", testService.listTests(title));
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        model.addAttribute("answers", answerService.allAnswers());
        model.addAttribute("title", title);
        return "test";
    }

    @GetMapping("/test/{id}")
    public String testInfo(@PathVariable Long id, Model model, Principal principal) {
        Test test = testService.getTestById(id);
        model.addAttribute("questions", questionService.listQuestions(test.getTitle()));
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        model.addAttribute("test", test);
        model.addAttribute("variants", variantsService.listVariants());
        return "test-info";
    }
    @PostMapping("/test/save-answers")
    public String saveAnswers(
            @RequestParam("testIds") List<Long> testIds,
            @RequestParam("questionIds") List<Long> questionIds,
            @RequestParam("userIds") List<Long> userIds,
            @RequestParam("answer") List<Integer> answers,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        answerService.saveAnswers(testIds, questionIds, userIds, answers);
        redirectAttributes.addAttribute("testIds", testIds.get(0));
        redirectAttributes.addAttribute("userIds", userIds.get(0));
        return "redirect:/result/{userIds}/{testIds}";
    }

    @PostMapping("/test/create")
    public String createTest(Test test, RedirectAttributes redirectAttributes) throws IOException {
        testService.saveTest(test);
        redirectAttributes.addAttribute("id", test.getId());
        return "redirect:/test/create/{id}";
    }

    @GetMapping("/test/create/{id}")
    public String showTestCreate(@PathVariable Long id, Model model, Principal principal) throws IOException {
        Test test = testService.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        return "test-create";
    }

    @PostMapping("/test/create-questions")
    public String createQuestions(@RequestParam("title") String title,
                                  @RequestParam("descriptions") List<String> descriptions,
                                  @RequestParam("points") List<Integer> points,
                                  @RequestParam("var1") List<String> var1,
                                  @RequestParam("var2") List<String> var2,
                                  @RequestParam("var3") List<String> var3,
                                  @RequestParam("var4") List<String> var4,
                                  @RequestParam("answers") List<Integer> answers
    ) throws IOException {
        questionService.createQuestions(title, descriptions, points, answers);
        List<Question> questions = questionService.listQuestions(title);
        variantsService.createVariants(questions, var1, var2, var3, var4);
        return "redirect:/admin";
    }

    @PostMapping("/test/delete/{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return "redirect:/test";
    }

    @GetMapping("/test/edit/{id}")
    public String showTestUpdate(@PathVariable Long id, Model model, Principal principal) throws IOException {
        Test test = testService.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        return "test-update";
    }

    @PostMapping("/test/update/{id}")
    public String updateTest(@PathVariable Long id, Test test, RedirectAttributes redirectAttributes) throws IOException {
        testService.updateTest(id, test);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/test/edit/{id}";
    }

    @GetMapping("/test/edit-questions/{id}")
    public String showQuestionUpdate(@PathVariable Long id, Model model, Principal principal) throws IOException {
        Test test = testService.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("user", testService.getUserByPrincipal(principal));
        model.addAttribute("questions", questionService.listQuestionsByTest(test.getId()));
        model.addAttribute("variants", variantsService.listVariants());
        return "question-update";
    }

    @PostMapping("/test/update-questions")
    public String updateQuestions(@RequestParam("questionIds") List <Long> questionIds,
                                  @RequestParam("variantsIds") List <Long> variantsIds,
                                  @RequestParam("title") String title,
                                  @RequestParam("descriptions") List<String> descriptions,
                                  @RequestParam("points") List<Integer> points,
                                  @RequestParam("var1") List<String> var1,
                                  @RequestParam("var2") List<String> var2,
                                  @RequestParam("var3") List<String> var3,
                                  @RequestParam("var4") List<String> var4,
                                  @RequestParam("answers") List<Integer> answers) throws IOException {
        questionService.updateQuestions(questionIds, title, descriptions, points, answers);
        List<Question> questions = questionService.listQuestions(title);
        variantsService.updateVariants(variantsIds, questions, var1, var2, var3, var4);
        return "redirect:/test";
    }
}
