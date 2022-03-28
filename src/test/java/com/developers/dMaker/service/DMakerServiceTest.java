package com.developers.dMaker.service;

import com.developers.dMaker.Exception.DMakerErrorCode;
import com.developers.dMaker.Exception.DMakerException;
import com.developers.dMaker.dto.CreateDeveloper;
import com.developers.dMaker.entity.Developer;
import com.developers.dMaker.repository.DeveloperRepository;
import com.developers.dMaker.repository.RetiredDeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.developers.dMaker.Type.DeveloperLevel.SENIOR;
import static com.developers.dMaker.Type.DeveloperSkillType.FRONT_END;
import static com.developers.dMaker.Type.StatusCode.EMPLOYED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {
    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DMakerService dMakerService;

    private final Developer defaultDeveloper = Developer.builder()
            .developerLevel(SENIOR)
            .developerSkillType(FRONT_END)
            .experienceYear(12)
            .statusCode(EMPLOYED)
            .memberId("memberId")
            .name("name")
            .age(12)
            .build();

    private final CreateDeveloper.Request defaultCreateRequest = CreateDeveloper.Request.builder()
            .developerLevel(SENIOR)
            .developerSkillType(FRONT_END)
            .experienceYears(12)
            .memberId("memberId")
            .name("name")
            .age(12)
            .build();

    @Test
    void createDeveloperTest_success() {
        // given
        // validation 통과하기 위해서 선언
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());
        // 데이터 저장 시 캡쳐해서 값 검증
        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);

        // when
        dMakerService.createDeveloper(defaultCreateRequest);

        // then
        // verify developerRepository 1번 호출 되었을때
        verify(developerRepository, times(1))
                .save(captor.capture());

        Developer savedDeveloper = captor.getValue();
        assertEquals(SENIOR, savedDeveloper.getDeveloperLevel());
        assertEquals(FRONT_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(12, savedDeveloper.getExperienceYear());
    }

    @Test
    void createDeveloperTest_failed_with_duplicated() {
        // given
        // validation 통과하기 위해서 선언
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        // when
        // then
        DMakerException dMakerException = assertThrows(DMakerException.class,
                () -> dMakerService.createDeveloper(defaultCreateRequest)
        );

        /*
        assertEquals(DMakerErrorCode.NO_DEVELOPER, dMakerException.getDMakerErrorCode());
        -- console
        org.opentest4j.AssertionFailedError:
        Expected :NO_DEVELOPER
        Actual   :DUPLICATED_MEMBER_ID
         */
        assertEquals(DMakerErrorCode.DUPLICATED_MEMBER_ID, dMakerException.getDMakerErrorCode());
    }
}