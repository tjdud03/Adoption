package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;

    @Transactional
    public List<Breed> getList() {
        return breedRepository.findAll();
    }
}
