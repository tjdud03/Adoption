package com.example.PetProject.service;

import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("questionService")
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public List<Question> getList() {
        return questionRepository.findAll();
    }
}