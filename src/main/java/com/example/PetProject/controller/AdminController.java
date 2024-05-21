package com.example.PetProject.controller;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.service.FaqService;
import com.example.PetProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class AdminController {
    private final FaqService faqService;
    private final QuestionService questionService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService) {
        this.faqService = faqService;
        this.questionService = questionService;
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

    @GetMapping("/Bulletin")
    public String Bulletinpage(Model model){
        return "Bulletin";
    }

    @GetMapping("/adoptionwriting")
    public String adoptionwritingpage(Model model){
        return "adoptionwriting";
    }

    @GetMapping("/review")
    public String reviewpage(Model model){
        return "review";
    }

    @GetMapping("/boast")
    public String boastpage(Model model){
        return "boast";
    }


   /* @RequestMapping(value = "/sponsor", method = RequestMethod.GET)*/
   @GetMapping("/sponsor")
    public String sponsor(Model model){
        return "sponsor";
    }


}