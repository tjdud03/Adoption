package com.example.PetProject.repository;

import com.example.PetProject.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<FAQ, Integer> {
}
