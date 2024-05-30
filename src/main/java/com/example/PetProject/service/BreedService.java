package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        int delResult = 0;
        try {
            List<Breed> breedToDelete = breedRepository.findAllById(breedIds);
            breedRepository.deleteAll(breedToDelete);
            delResult = 1;
        } catch (Exception e) {
            delResult = 0;
        } finally {
            return delResult;
        }
    }
   /* @Transactional
    public Page<Breed> getReportedAndDeletedBreeds(Pageable pageable) {
        return breedRepository.findByStateIn(Arrays.asList("신고", "삭제"), pageable);
    }*/

}
