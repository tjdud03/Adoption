package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.repository.FaqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    // 페이지에 목록 조회
    @Transactional
    public List<FAQ> getList() {
        return faqRepository.findAll();
    }

    // 선택된 체크박스 가져와서 delete
    @Transactional
    public int deleteFAQs(List<Integer> faqIds) {
        int delResult = 0;
        try {
            List<FAQ> faqsToDelete = faqRepository.findAllById(faqIds);
            faqRepository.deleteAll(faqsToDelete);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }

    @Transactional
    public Optional<FAQ> detailFAQs(Integer faqId){
        Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        return faqOptional;
    }

    @Transactional
    public int updateFAQs(FAQ faq_updateOp){

        FAQ faqToUpdate = faqRepository.findById(faq_updateOp.getFaq_id()).orElse(null);
        int resultCode = 0;
        if (faqToUpdate != null) {
            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            faqToUpdate.setMember_id(faq_updateOp.getMember_id());
            faqToUpdate.setAdd_date(LocalDateTime.now().toString());
            faqToUpdate.setChange_date(LocalDateTime.now().toString());
            faqToUpdate.setTitle(faq_updateOp.getTitle());
            faqToUpdate.setContent(faq_updateOp.getContent());

            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            faqRepository.save(faqToUpdate);
            resultCode = 0;
        } else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        return resultCode;
    }

    @Transactional
    public int insertFAQs(FAQ faq_insertOp){
        System.out.println(faq_insertOp);
        return 0;
        /*FAQ newFaq = new FAQ();

        newFaq.setAdd_date(LocalDateTime.now().toString());
        newFaq.setChange_date(LocalDateTime.now().toString());
        newFaq.setTitle(faq_createOp.getTitle());
        newFaq.setContent(faq_createOp.getContent());

        FAQ insertedFaq = faqRepository.save(newFaq);

        if (insertedFaq != null) {
            return 0;
        } else {
            return 1;
        }*/

        /*FAQ faqToCreate = faqRepository.save(faq_createOp);

        int resultCode = 0;
        if (faqToCreate != null) {
            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            faqToCreate.setMember_id(faqToCreate.getMember_id());
            faqToCreate.setAdd_date(LocalDateTime.now().toString());
            faqToCreate.setChange_date(LocalDateTime.now().toString());
            faqToCreate.setTitle(faqToCreate.getTitle());
            faqToCreate.setContent(faqToCreate.getContent());

            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            faqRepository.save(faqToCreate);
            resultCode = 0;
        } else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        System.out.println(faqToCreate);
        return 0;*/
    }
}