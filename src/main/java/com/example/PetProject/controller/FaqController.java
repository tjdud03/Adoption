package com.example.PetProject.controller;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/test")
    public List<FAQ> test(){
        List<FAQ> faq_list = faqService.save();
        return faq_list;
    }
}
