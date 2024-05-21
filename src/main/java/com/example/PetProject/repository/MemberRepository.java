package com.example.PetProject.repository;

import com.example.PetProject.domain.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberDTO, Integer> {
}
