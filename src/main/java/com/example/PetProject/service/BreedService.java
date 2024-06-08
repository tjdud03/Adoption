package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;

    @Transactional
    public List<Breed> getReportedAndDeletedBreeds() {
        return breedRepository.findByStateIn(Arrays.asList("신고", "삭제"));
    }

    @Transactional
    public Breed getBreedById(Integer id) {
        return breedRepository.findById(id).orElseThrow(() -> new RuntimeException("Breed not found"));
    }

    @Transactional
    public int deleteBreed(List<Integer> breedIds) {
        return 0;
    }

    public List<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }

    public List<Breed> findBreedsWithNullState() {
        return breedRepository.findByStateIsNull();
    }

    public Page<Breed> findAll(Pageable pageable) {
        return breedRepository.findAll(pageable);
    }

    public List<Breed> getList() {
        return breedRepository.findAll();
    }

    public List<Breed> findAllBreeds() {
        // 모든 품종을 반환하는 로직을 여기에 구현합니다.
        return breedRepository.findAll();  // Ensure to use the repository's method to get all breeds
    }
}

