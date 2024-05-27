package com.example.PetProject.controller;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.BreedRepository;
import com.example.PetProject.service.BreedService;
import com.example.PetProject.service.FaqService;
import com.example.PetProject.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final FaqService faqService;
    private final QuestionService questionService;
    private final BreedService breedService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService
    , BreedService breedService) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.breedService = breedService;
    }

    @GetMapping("/faq")
    public String faqpage(Model model){
        List<FAQ> faq_list = faqService.getList();
        model.addAttribute("faq_list", faq_list);
        return "faq_admin";
    }

    @GetMapping("/question")
    public String questionpage(Model model){
        List<Question> question_list = questionService.getList();
        model.addAttribute("question_list", question_list);
        return "question_admin";
    }

    @GetMapping("/breed")
    public String breedpage(Model model){
        List<Breed> breed_list = breedService.getReportedAndDeletedBreeds();
        model.addAttribute("breed_list", breed_list);
        return  "breed";
    }


    @GetMapping("/index")
    public String indexpage(){
        return "redirect:/index.html";
    }

    @GetMapping("/banner")
    public String bannerpage(Model model){
        return "banner_admin";
    }

    @GetMapping("/manager")
    public String managerpage(){
        return "manager";
    }

    @GetMapping("/Isponsor")
    public String isponsorpage(){
        return "Isponser";
    }

    @GetMapping("/Lsponsor")
    public String lsponsorpage(){
        return "Lsponsor";
    }

    @GetMapping("/community")
    public String communitypage(){
        return "community";
    }

    @GetMapping("/review")
    public String reviewpage(){
        return "review";
    }

    @GetMapping("/boast")
    public String boastpage(){
        return "boast";
    }


   /* @RequestMapping(value = "/sponsor", method = RequestMethod.GET)*/
   @GetMapping("/sponsor")
    public String sponsor(Model model){
        return "sponsor";
    }
}