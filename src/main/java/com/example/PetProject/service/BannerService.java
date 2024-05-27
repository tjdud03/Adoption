package com.example.PetProject.service;

import com.example.PetProject.domain.Banner;
import com.example.PetProject.repository.BannerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    @Transactional
    public List<Banner> getList() {
        return bannerRepository.findAll();
    }
}
