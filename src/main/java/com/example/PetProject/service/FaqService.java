package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.repository.FaqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public List<FAQ> getList() {
        return faqRepository.findAll();
    }

    @Transactional
    public int deleteFAQs(List<Integer> faqIds) {
        int delResult = 0;
        try {
            List<FAQ> faqsToDelete = faqRepository.findAllById(faqIds);
            faqRepository.deleteAll(faqsToDelete);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }

    @Transactional
    public int detailFAQs(Integer faqId){
        Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        return 0;
    }
}