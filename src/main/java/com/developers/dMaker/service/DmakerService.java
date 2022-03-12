package com.developers.dMaker.service;

import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.DeveloperSkillType;
import com.developers.dMaker.dto.CreateDeveloper;
import com.developers.dMaker.entity.Developer;
import com.developers.dMaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Slf4j
@Service
@RequiredArgsConstructor
public class DmakerService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper(CreateDeveloper.Request request) {
        // 트랜잭샨 사용 예
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNGNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYear(2)
                .name("Olaf")
                .age(5)
                .build();

        developerRepository.save(developer);
    }
}
