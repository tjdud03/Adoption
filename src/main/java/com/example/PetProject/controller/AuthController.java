package com.example.PetProject.controller;

import com.example.PetProject.domain.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
//    private final AuthService authService;

    @PostMapping("/login_a")
    public ResponseEntity<String> getMemberProfile(
            @Validated @RequestBody LoginRequestDto request
    ){
//        String token = this.authService.login(request);
//        return ResponseEntity.status(HttpStatus.OK).body(token);
        return null;
    }
/*    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    private final LoginService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login_Page")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> logindata) {
        // 이메일로 회원 조회
        String email = logindata.get("email");
        String password = logindata.get("password");
        Member member = memberRepository.findByEmailAndPw(email, password);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        // 토큰 생성
        String token = tokenProvider.generateToken(email);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}

class JwtAuthenticationResponse {
    private String token;
    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }*/
}