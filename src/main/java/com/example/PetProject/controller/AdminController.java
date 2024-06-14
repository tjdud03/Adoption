package com.example.PetProject.controller;

import com.example.PetProject.domain.*;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class AdminController {
    private final FaqService faqService;
    private final QuestionService questionService;
    private final BannerService bannerService;
    private final MemberService memberService;
    private final LoginService loginService;
    private final CommunityService communityService;


    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService, BannerService bannerService,
                           MemberService memberService, LoginService loginService, CommunityService communityService) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.bannerService = bannerService;
        this.memberService = memberService;
        this.loginService = loginService;
        this.communityService = communityService;
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> logindata) {

        ResponseEntity<?> token = loginService.login(logindata);
        return token;
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
    public String memberpage(Model model) {
        List<Member> member_list = memberService.getList();
        model.addAttribute("member_list", member_list);
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

    /*@ResponseBody
    @RequestMapping(value = {"/faq_create"}, method = {RequestMethod.GET})
    public Optional detail_faq(@RequestParam("faqId") Integer faqId) {
        //System.out.println(faqId);
*/
        //삭제버튼 누를시 db delete
        @ResponseBody
        @RequestMapping(value = {"/delete_faq"}, method = {RequestMethod.POST})
        public int delete_faq (@RequestBody List < Integer > faqIds) {
            return faqService.deleteFAQs(faqIds);
        }

        //수정 및 상세정보 페이지 띄우기
        @RequestMapping(value = {"/faq_detail"}, method = {RequestMethod.GET})
        public String detail_faq (@RequestParam(value = "faqId", required = false) Integer faqId,
                Model model){
            Optional<FAQ> faq_detailList = faqService.detailFAQs(faqId);
            model.addAttribute("faq_detailList", faq_detailList);
            return "faq_detail";
        }

        // 수정버튼 누를 시 입력되어있는 정보 db에 update
        @ResponseBody
        @RequestMapping(value = {"/update_faq"}, method = {RequestMethod.POST})
        public int update_faq (@RequestBody FAQ faq_updateOp){
            return faqService.updateFAQs(faq_updateOp);
        }

        //등록 미완성 로그인 구현 후 완성

        //등록페이지 띄우기
        @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.GET})
        public String insertPage_faq () {
            return "faq_insert";
        }

        //등록버튼 누르면 db에 insert
        @ResponseBody
        @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.POST})
        public int insert_faq (@RequestBody FAQ faq_insertOp){
            return faqService.insertFAQs(faq_insertOp);
        }
    }


