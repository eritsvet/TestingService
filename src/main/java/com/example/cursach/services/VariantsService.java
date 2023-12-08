package com.example.cursach.services;

import com.example.cursach.models.*;
import com.example.cursach.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.ArrayList;
@Service
@Slf4j
@RequiredArgsConstructor
public class VariantsService {
    private final VariantsRepository variantsRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

//    public List<Variants> listVariants(Long id) {
//        if (id != 0) return variantsRepository.findByQuestionId(id);
//        return variantsRepository.findAll();
//    }
    public List<Variants> listVariants() {
        return variantsRepository.findAll();
    }


    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void createVariants(List<Question> questions, List<String> var1, List<String> var2, List<String> var3, List<String> var4) {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String title1 = var1.get(i);
            String title2 = var2.get(i);
            String title3 = var3.get(i);
            String title4 = var4.get(i);

            Variants newVariant1 = new Variants();
            Variants newVariant2 = new Variants();
            Variants newVariant3 = new Variants();
            Variants newVariant4 = new Variants();
            newVariant1.setQuestion(question);
            newVariant1.setTitle(title1);
            newVariant2.setQuestion(question);
            newVariant2.setTitle(title2);
            newVariant3.setQuestion(question);
            newVariant3.setTitle(title3);
            newVariant4.setQuestion(question);
            newVariant4.setTitle(title4);

            variantsRepository.save(newVariant1);
            variantsRepository.save(newVariant2);
            variantsRepository.save(newVariant3);
            variantsRepository.save(newVariant4);
        }
    }

    public void updateVariants(List <Long> variantsIds, List<Question> questions, List<String> var1, List<String> var2, List<String> var3, List<String> var4) {
        for (int i = 0; i < variantsIds.size(); i++) {
            Variants existingVariant = variantsRepository.findById(variantsIds.get(i)).orElse(null);
            String title1 = var1.get(i/4);
            String title2 = var2.get(i/4);
            String title3 = var3.get(i/4);
            String title4 = var4.get(i/4);
            if (existingVariant != null) {
                if (variantsIds.get(i)%4==1) existingVariant.setTitle(title1);
                if (variantsIds.get(i)%4==2) existingVariant.setTitle(title2);
                if (variantsIds.get(i)%4==3) existingVariant.setTitle(title3);
                if (variantsIds.get(i)%4==0) existingVariant.setTitle(title4);

                variantsRepository.save(existingVariant);
            }
        }
        for (int i = 0; i < questions.size(); i++) {
            for (int j = 1; j <=4; j++) {

            }
        }
    }
}