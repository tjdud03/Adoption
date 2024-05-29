package com.example.PetProject.repository;

import com.example.PetProject.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
    List<Breed> findByStateIn(List<String> states);
    /*Page<Breed> findByStateIn(List<String> states, Pageable pageable);*/
}
