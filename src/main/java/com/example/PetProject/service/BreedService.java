package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;
import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
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

    @Transactional
    public List<Breed> searchBreeds(String type, String keyword) {
        if (type.equals("breed")) {
            return breedRepository.findByBreedContaining(keyword);
        } else if (type.equals("state")) {
            return breedRepository.findByStateContaining(keyword);
        } else if (type.equals("title")) {
            return breedRepository.findByTitleContaining(keyword);
        } else if (type.equals("member_id")) {
            return breedRepository.findByMember_IdContaining(keyword);
        } else {
            return breedRepository.findAll();
        }
    }
   /* public Page<Breed> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Breed> breeds =
                BreedRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("breed.getContent() = " + breeds.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("breed.getTotalElements() = " + breeds.getTotalElements()); // 전체 글갯수
        System.out.println("breed.getNumber() = " + breeds.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("breed.getTotalPages() = " + breeds.getTotalPages()); // 전체 페이지 갯수
        System.out.println("breed.getSize() = " + breeds.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("breed.hasPrevious() = " + breeds.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("breed.isFirst() = " + breeds.isFirst()); // 첫 페이지 여부
        System.out.println("breed.isLast() = " + breeds.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }*/

    /* @Transactional
     public Page<Breed> getReportedAndDeletedBreeds(Pageable pageable) {
         return breedRepository.findByStateIn(Arrays.asList("신고", "삭제"), pageable);
     }*/

}

