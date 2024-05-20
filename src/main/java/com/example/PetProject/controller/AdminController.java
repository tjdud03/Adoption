package com.example.PetProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
