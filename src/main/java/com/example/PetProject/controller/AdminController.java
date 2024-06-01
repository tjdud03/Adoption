package com.example.PetProject.controller;

import com.example.PetProject.domain.*;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.service.BannerService;
import com.example.PetProject.service.BreedService;
import com.example.PetProject.service.FaqService;
import com.example.PetProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/breed")
    public String breedpage(Model model){
        List<Breed> breed_list = breedService.getReportedAndDeletedBreeds();
        model.addAttribute("breed_list", breed_list);
        return "breed";
    }

    @GetMapping("/breed/view/{id}")
    public String viewBreed(@PathVariable("id") Integer id, Model model) {
        Breed breed = breedService.getBreedById(id);
        model.addAttribute("breed", breed);
        return "breed_view";
    }

    /*@GetMapping("/breed")
    public String breedpage(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Breed> breedPage = breedService.getReportedAndDeletedBreeds(PageRequest.of(page, 10));
        model.addAttribute("breedPage", breedPage);
        return "breed";
    }*/

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
    public String communitypage(){
        return "community";
    }

    @ResponseBody
    @RequestMapping(value = {"/delete_faq"}, method = { RequestMethod.POST })
    public int delete_faq(@RequestBody List<Integer> faqIds) {
        return faqService.deleteFAQs(faqIds);
    }


    @RequestMapping(value = {"/faq_detail"} , method = { RequestMethod.GET })
    public String detail_faq(@RequestParam(value = "faqId", required=false) Integer faqId) {
        //System.out.println(faqId);
        //faqService.detailFAQs();
        //Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        //System.out.println(faqOptional);
        return "faq_detail";
    }

    @ResponseBody
    @RequestMapping(value =  {"/delete_breed"}, method = {RequestMethod.POST})
    public  int delete_breed(@RequestBody List<Integer> breedIds){
        return breedService.deleteBreed(breedIds);
    }
    @RequestMapping(value = {"/breed_view"}, method = {RequestMethod.GET})
    public String view_breed(@RequestParam(value = "breedIds", required = false) Integer breedIds){
        return "breed_view";
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


    // /breed/paging? page=1 (기본 1페이지 줄거임)
   /* @GetMapping("/breed")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<Breed> breedList = breedService.paging(pageable);
        int blockLimit = 10;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < breedList.getTotalPages()) ? startPage + blockLimit - 1 : breedList.getTotalPages();

        model.addAttribute("breedList", breedList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "breed";
    }*/

}
