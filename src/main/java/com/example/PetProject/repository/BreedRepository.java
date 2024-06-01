package com.example.PetProject.repository;

import com.example.PetProject.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
    List<Breed> findByStateIn(List<String> states);

    List<Breed> findByBreedContaining(String keyword);

    List<Breed> findByStateContaining(String keyword);

    List<Breed> findByTitleContaining(String keyword);

    List<Breed> findByMember_IdContaining(String keyword);
}
