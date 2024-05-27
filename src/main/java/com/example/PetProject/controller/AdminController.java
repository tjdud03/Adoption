package com.example.PetProject.controller;

import com.example.PetProject.domain.Banner;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.service.BannerService;
import com.example.PetProject.service.FaqService;
import com.example.PetProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class AdminController {
    private final FaqService faqService;
    private final QuestionService questionService;
    private final BannerService bannerService;
    private final FaqRepository faqRepository;

    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService, BannerService bannerService, FaqRepository faqRepository) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.bannerService = bannerService;
        this.faqRepository = faqRepository;
    }

    @GetMapping("/faq")
    public String faqpage(Model model) {
        List<FAQ> faq_list = faqService.getList();
        model.addAttribute("faq_list", faq_list);
        return "faq_admin";
    }

    @GetMapping("/question")
    public String questionpage(Model model) {
        List<Question> question_list = questionService.getList();
        model.addAttribute("question_list", question_list);
        return "question_admin";
    }

    @GetMapping("/banner")
    public String bannerpage(Model model) {
        List<Banner> banner_list = bannerService.getList();
        model.addAttribute("banner_list", banner_list);
        return "banner_admin";
    }

    @GetMapping("/index")
    public String indexpage() {
        return "redirect:/index.html";
    }

    @GetMapping("/member")
    public String memberpage() {
        return "member";
    }

    @GetMapping("/manager")
    public String managerpage() {
        return "manager";
    }

    @GetMapping("/Isponsor")
    public String isponsorpage() {
        return "Isponsor";
    }

    @GetMapping("/Lsponsor")
    public String lsponsorpage() {
        return "Lsponsor";
    }

    @GetMapping("/sponsor")
    public String sponsor(Model model) {
        return "sponsor";
    }

    @ResponseBody
    @RequestMapping(value = {"/delete_faq"}, method = { RequestMethod.POST })
    public int delete_faq(@RequestBody List<Integer> faqIds) {
        return faqService.deleteFAQs(faqIds);
    }


    @RequestMapping(value = {"/faq_detail"}, method = { RequestMethod.GET })
    public String detail_faq(@RequestParam(value = "faqId", required=false) Integer faqId) {
        //System.out.println(faqId);

        Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        //System.out.println(faqOptional);
        return "faq_detail";
    }
    /*@ResponseBody
    @RequestMapping(value = {"/faq_create"}, method = { RequestMethod.GET })
    public Optional detail_faq(@RequestParam("faqId") Integer faqId) {
        //System.out.println(faqId);

        Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        //System.out.println(faqOptional);
        return faqOptional;
    }*/
    /*@ResponseBody
    @RequestMapping(value = {"/faq_detail"}, method = { RequestMethod.GET })
    public int detail_faq(@RequestParam("faqId") Integer faqId, Optional<FAQ> faqOptional) {
        //System.out.println(faqId);
        // Integer faqId

        //System.out.println(faqOptional);
        //return faqOptional;
        return faqService.detailFAQs(faqId);
    }*/
}
