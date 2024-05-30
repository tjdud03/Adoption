package com.example.PetProject.controller;

import com.example.PetProject.domain.Banner;
import com.example.PetProject.domain.Breed;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.service.BannerService;
import com.example.PetProject.service.BreedService;
import com.example.PetProject.service.FaqService;
import com.example.PetProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {
    private final FaqService faqService;
    private final QuestionService questionService;
    private final BannerService bannerService;
    private final FaqRepository faqRepository;
    private final BreedService breedService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService, BannerService bannerService, FaqRepository faqRepository, BreedService breedService) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.bannerService = bannerService;
        this.faqRepository = faqRepository;
        this.breedService = breedService;
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
    public String sponsor() {
        return "sponsor";
    }

    @GetMapping("/community")
    public String communitypage() {
        return "community";
    }

    @GetMapping("/review")
    public String reviewpage() {
        return "review";
    }

    @GetMapping("/boast")
    public String boastpage() {
        return "boast";
    }

    //삭제버튼 누를시 db delete
    @ResponseBody
    @RequestMapping(value = {"/delete_faq"}, method = {RequestMethod.POST})
    public int delete_faq(@RequestBody List<Integer> faqIds) {
        return faqService.deleteFAQs(faqIds);
    }

    //수정 및 상세정보 페이지 띄우기
    @RequestMapping(value = {"/faq_detail"}, method = {RequestMethod.GET})
    public String detail_faq(@RequestParam(value = "faqId", required = false) Integer faqId,
                             Model model) {
        Optional<FAQ> faq_detailList = faqService.detailFAQs(faqId);
        model.addAttribute("faq_detailList", faq_detailList);
        return "faq_detail";
    }
    
    // 수정버튼 누를 시 입력되어있는 정보 db에 update
    @ResponseBody
    @RequestMapping(value = {"/update_faq"}, method = {RequestMethod.POST})
    public int update_faq(@RequestBody FAQ faq_updateOp){
        return faqService.updateFAQs(faq_updateOp);
    }

    //등록 미완성 로그인 구현 후 완성

    //등록페이지 띄우기
    @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.GET})
    public String insertPage_faq() {
        return "faq_insert";
    }

    //등록버튼 누르면 db에 insert
    @ResponseBody
    @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.POST})
    public int insert_faq(@RequestBody FAQ faq_insertOp){
        return faqService.insertFAQs(faq_insertOp);
    }
}
