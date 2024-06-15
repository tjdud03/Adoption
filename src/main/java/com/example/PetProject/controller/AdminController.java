package com.example.PetProject.controller;

import com.example.PetProject.domain.*;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.repository.AnswerRepository;
import com.example.PetProject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;

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
    private final BreedService breedService;
    private final MemberService memberService;
    private final LoginService loginService;
    private final UserService userService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public AdminController(FaqService faqService, QuestionService questionService, BannerService bannerService,
                           BreedService breedService, MemberService memberService, LoginService loginService, AnswerRepository answerRepository) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.bannerService = bannerService;
        this.breedService = breedService;
        this.memberService = memberService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> logindata) {

        ResponseEntity<?> token = loginService.login(logindata);
        return token;
    }

    // 관리자페이지 faq목록 띄우기
    // 회원가입 페이지 출력 요청 - GetMapping으로 출력 요청 -> PostMapping에서 form에 대한 action 수행
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String join(@ModelAttribute UserDTO userDTO) {
        System.out.println("AdminController.save");
        System.out.println("userDTO = " + userDTO);
        userService.save(userDTO);

        return "redirect:/index";
    }

    @GetMapping("/index")
    public String indexpage() {
        return "index";
    }

    @GetMapping("/faq")
    public String faqpage(Model model) {
        List<FAQ> faq_list = faqService.getList();
        model.addAttribute("faq_list", faq_list);
        return "faq_admin";
    }

    // 사용자페이지 faq목록 띄우기
    @GetMapping("/faqUser")
    public String faqUserpage(Model model) {
        List<FAQ> faq_list = faqService.getList();
        model.addAttribute("faq_list", faq_list);
        return "faq_user";
    }

    // 관리자페이지 문의 목록띄우기
    @GetMapping("/question")
    public String questionpage(Model model) {
        List<Question> question_list = questionService.getList();
        model.addAttribute("question_list", question_list);
        List<Answer> answer_list =  questionService.get_answerList();
        model.addAttribute("answer_list", answer_list);
        return "question_admin";
    }

    @GetMapping("/questionUser")
    public String questionUserpage(Model model) {
        List<Question> questionUser_list = questionService.getList();
        model.addAttribute("questionUser_list", questionUser_list);
        List<Answer> answerUser_list =  questionService.get_answerList();
        model.addAttribute("answerUser_list", answerUser_list);
        return "question_user";
    }

    /*@GetMapping("/questionUser")
    public String questionUserpage(Model model, @RequestParam("memberId") Integer memberId, @RequestParam("qId") Integer qId) {
        Optional<Question> questionUser_list = questionService.getUserList(memberId);
        model.addAttribute("questionUser_list", questionUser_list);
        Optional<Answer> answerUser_list =  questionService.getUser_answerList(qId);
        model.addAttribute("answerUser_list", answerUser_list);
        return "question_user";
    }*/

    // 관리자페이지 배너 목록띄우기
    @GetMapping("/banner")
    public String bannerpage(Model model) {
        List<Banner> banner_list = bannerService.getList();
        model.addAttribute("banner_list", banner_list);
        return "banner_admin";
    }

    @GetMapping("/breed")
    public String breedpage(Model model) {
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

    @GetMapping("/member")
    public String memberpage(Model model) {
        List<Member> member_list = memberService.getList();
        model.addAttribute("member_list", member_list);
        return "member";
    }

    //등록페이지 띄우기
    @RequestMapping(value = {"/insert_mem"}, method = {RequestMethod.GET})
    public String insertPage_mem() {
        return "insert_mem";
    }

    //등록버튼 누르면 db에 insert
    @ResponseBody
    @RequestMapping(value = {"/insert_mem"}, method = {RequestMethod.POST})
    public int insert_mem(@RequestBody Member mem_insertOp){
        return memberService.insertMember(mem_insertOp);
    }

    @ResponseBody
    @RequestMapping(value = {"/delete_mem"}, method = {RequestMethod.POST})
    public int delete_mem(@RequestBody List<Integer> memberIds) {
        return memberService.deleteMember(memberIds);
    //회원 삭제
    @ResponseBody
    @RequestMapping(value = {"/delete_mem"}, method = {RequestMethod.POST})
    public int delete_mem(@RequestBody List<Integer> memberIds) {
        return memberService.deleteMember(memberIds);
    }

    @GetMapping("/sponsor")
    public String sponsor() {
        return "sponsor";
    }

    @GetMapping("/sponMain")
    public String sponMain() {
        return "sponMain";
    }

    @GetMapping("/spon")
    public String sponMain() {
        return "spon";
    }

    @GetMapping("/community")
    public String communitypage(){
        return "community";
    }

    @ResponseBody
    @RequestMapping(value = {"/delete_breed"}, method = {RequestMethod.POST})
    public int delete_breed(@RequestBody List<Integer> breedIds) {
        return breedService.deleteBreed(breedIds);
    }

    @RequestMapping(value = {"/breed_view"}, method = {RequestMethod.GET})
    public String view_breed(@RequestParam(value = "breedIds", required = false) Integer breedIds) {
        return "breed_view";
    }

    @GetMapping("/breed_user")
    public String getBreedUserPage(Model model) {
        List<Breed> breedList = breedService.getAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "강아지".equals(breed.getType()))
                .collect(Collectors.toList()); //null값 허용, 수정 허용
        model.addAttribute("breed_list", breedList);
        return "breed_user";
    }

    @ResponseBody
    @GetMapping("/search")
    public List<Breed> searchBreeds(@RequestParam String query) {
        List<Breed> allBreeds = breedService.findAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "강아지".equals(breed.getType()))
                .collect(Collectors.toList());
        return allBreeds.stream()
                .filter(breed -> breed.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
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

    // 사용자페이지 faq상세페이지
    @RequestMapping(value = {"/faq_detailUser"}, method = {RequestMethod.GET})
    public String detailUser_faq(@RequestParam(value = "faqId", required = false) Integer faqId,
                             Model model) {
        Optional<FAQ> faq_detailList = faqService.detailFAQs(faqId);
        model.addAttribute("faq_detailList", faq_detailList);
        return "faq_detailUser";
    }

    // 수정버튼 누를 시 입력되어있는 정보 db에 update
    @ResponseBody
    @RequestMapping(value = {"/update_faq"}, method = {RequestMethod.POST})
    public int update_faq(@RequestBody FAQ faq_updateOp){
        return faqService.updateFAQs(faq_updateOp);
    }
        // 수정버튼 누를 시 입력되어있는 정보 db에 update
        @ResponseBody
        @RequestMapping(value = {"/update_faq"}, method = {RequestMethod.POST})
        public int update_faq (@RequestBody FAQ faq_updateOp){
            return faqService.updateFAQs(faq_updateOp);
        }

    //등록페이지 띄우기
    @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.GET})
    public String insertPage_faq() {
        return "faq_insert";
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
    public int insert_faq(@RequestBody FAQ faq_insertOp){
        return faqService.insertFAQs(faq_insertOp);
    }

    @RequestMapping(value = {"/insert_question"}, method = {RequestMethod.GET})
    public String insertPage_questionUser() {
        return "question_insertUser";
    }

    @ResponseBody
    @RequestMapping(value = {"/insert_question"}, method = {RequestMethod.POST})
    public int insert_faq(@RequestBody Question question_insertOp){
        return questionService.insertQs(question_insertOp);
    }

    @RequestMapping(value = {"/detailUser_Q"}, method = {RequestMethod.GET})
    public String detailUser_question(@RequestParam(value = "qId", required = false) Integer qId,
                                  Model model){

        Optional<Question> questionUser_detailList = questionService.detail_questionQs(qId);
        Optional<Answer> answerUser_detailList = questionService.detail_answerQs(qId);
        model.addAttribute("questionUser_detailList", questionUser_detailList);
        model.addAttribute("answerUser_detailList", answerUser_detailList);
        /*Optional<Answer> answer_detailList = questionService.detailQs(qId);*/
        return "question_detailUser";
    }

    @RequestMapping(value = {"/updateUser_Q"}, method = {RequestMethod.GET})
    public int updateUser_question(@RequestBody Question question_updateOp){

        return questionService.updateUserQs(question_updateOp);
    }

    // 삭제버튼 누르면 삭제
    @ResponseBody
    @RequestMapping(value = {"/deleteQ"}, method = {RequestMethod.POST})
    public int delete_Q(@RequestBody List<Integer> qids){
        return questionService.deleteQs(qids);
    }

    @ResponseBody
    @RequestMapping(value = {"/deleteQ_User"}, method = {RequestMethod.POST})
    public int deleteUser_Q(@RequestBody List<Integer> qids){
        return questionService.deleteQs(qids);
    }

    // 문의 관리 수정 및 상세페이지
    @RequestMapping(value = {"/detailQ"}, method = {RequestMethod.GET})
    public String detail_question(@RequestParam(value = "qId", required = false) Integer qId,
                                  Model model){

        Optional<Question> question_detailList = questionService.detail_questionQs(qId);
        Optional<Answer> answer_detailList = questionService.detail_answerQs(qId);
        model.addAttribute("question_detailList", question_detailList);
        model.addAttribute("answer_detailList", answer_detailList);
        /*Optional<Answer> answer_detailList = questionService.detailQs(qId);*/
        return "question_detail";
    }

    @ResponseBody
    @RequestMapping(value = {"/update_Q"}, method = {RequestMethod.POST})
    public int update_question(@RequestBody Question question_updateOp, Answer answer_updateOp){
        return questionService.updateQs(question_updateOp, answer_updateOp);
    }

    // 배너 상세페이지
    @RequestMapping(value = {"/banner_detail"}, method = {RequestMethod.GET})
    public String detail_banner(@RequestParam(value = "bannerId", required = false) Integer bannerId,
                                  Model model){
        Optional<Banner> banner_detailList = bannerService.detailbanners(bannerId);
        model.addAttribute("banner_detailList", banner_detailList);
        return "banner_detail";
    }

    // 배너 등록페이지
    @RequestMapping(value = {"/insert_banner"}, method = {RequestMethod.GET})
    public String insertPage_banner() {
        return "banner_insert";
    }

    //배너 등록페이지 등록insert하기
    @ResponseBody
    @RequestMapping(value = {"/insert_banner"}, method = {RequestMethod.POST})
    public int insert_banner(@RequestBody Banner banner_insertOp){
        return bannerService.insertBanners(banner_insertOp);
    }


    // banner 수정update하기
    @ResponseBody
    @RequestMapping(value = {"/update_banner"}, method = {RequestMethod.POST})
    public int update_banner(@RequestBody Banner banner_updateOp){
        return bannerService.updateBanners(banner_updateOp);
    }

    // banner 삭제delete하기
    @ResponseBody
    @RequestMapping(value = {"/delete_banner"}, method = {RequestMethod.POST})
    public int delete_banner(@RequestBody List<Integer> bannerids){
        return bannerService.deleteBanners(bannerids);
    }
}
        //등록버튼 누르면 db에 insert
        @ResponseBody
        @RequestMapping(value = {"/insert_faq"}, method = {RequestMethod.POST})
        public int insert_faq (@RequestBody FAQ faq_insertOp){
            return faqService.insertFAQs(faq_insertOp);
        }
    }
