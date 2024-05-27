package com.example.PetProject.repository;

import com.example.PetProject.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
}
