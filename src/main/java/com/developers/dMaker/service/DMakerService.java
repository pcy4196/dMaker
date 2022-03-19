package com.developers.dMaker.service;

import com.developers.dMaker.Exception.DMakerException;
import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.StatusCode;
import com.developers.dMaker.dto.CreateDeveloper;
import com.developers.dMaker.dto.DeveloperDetailDto;
import com.developers.dMaker.dto.DeveloperDto;
import com.developers.dMaker.dto.EditDeveloper;
import com.developers.dMaker.entity.Developer;
import com.developers.dMaker.entity.RetiredDeveloper;
import com.developers.dMaker.repository.DeveloperRepository;
import com.developers.dMaker.repository.RetiredDeveloperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.developers.dMaker.Exception.DMakerErrorCode.*;
import static com.developers.dMaker.Type.StatusCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        // business logic start
        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYear(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    public List<DeveloperDto> getAllEmplyedDevelopers() {
        return developerRepository.findDeveloperByStatusCodeEquals(EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER)); // 값이 없을때 오류 반환
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(memberId, request);

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYear(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    // 파라미터 CHECK(Create)
    private void validateCreateDeveloperRequest(CreateDeveloper.Request request)
            throws DMakerException {
        // Business Validation
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();
        validateDeveloperLevel(developerLevel, experienceYears);

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent(developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                });
    }

    // 파라미터 CHECK(Update)
    private void validateEditDeveloperRequest(String memberId, EditDeveloper.Request request) {
        // Business Validation
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();
        validateDeveloperLevel(developerLevel, experienceYears);
    }

    private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {
        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYears < 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        } else if (developerLevel == DeveloperLevel.JUNGNIOR
                && (experienceYears < 4 || experienceYears > 10)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        } else if (developerLevel == DeveloperLevel.JUNIOR
                && experienceYears > 4) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // 1. EMPLOYED -> RETIRED
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
        developer.setStatusCode(RETIRED);

        // 2. save into retiredDeveloper
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
