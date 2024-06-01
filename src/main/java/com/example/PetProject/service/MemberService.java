package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.MemberDTO;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MemberRepository;

    @Transactional
    public List<Member> save() {
        return MemberRepository.findAll();
    }
}
