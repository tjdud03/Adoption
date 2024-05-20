package com.example.PetProject.controller;

import ch.qos.logback.core.model.Model;
import com.example.PetProject.domain.MemberDTO;
import com.example.PetProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class AdminController {

    @GetMapping("/banner")
    public String bannerpage(){
        return "banner_admin";
    }
    @GetMapping("/faq")
    public String faqpage(){
        return "faq_admin";
    }
    @GetMapping("/isponsor")
    public String isponsorpage(){
        return "Isponser";
    }
    @GetMapping("/lsponsor")
    public String lsponsorpage(){
        return "Lsponsor";
    }
    @GetMapping("/question")
    public String questionpage(){
        return "question_admin";
    }
    @GetMapping("/sponsoer")
    public String sponsorpage(){
        return "sponsor";
    }

    private MemberService memberService;
}
