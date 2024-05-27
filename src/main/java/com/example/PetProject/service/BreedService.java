package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

}
