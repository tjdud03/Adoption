package com.example.PetProject.service;

import com.example.PetProject.domain.Member;
import com.example.PetProject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 페이지에 목록 조회
    @Transactional
    public List<Member> getList() {
        return memberRepository.findAll();
    }
}