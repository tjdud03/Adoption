package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Member;
import com.example.PetProject.repository.FaqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.PetProject.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public List<FAQ> save() {
        return faqRepository.findAll();
    }

}