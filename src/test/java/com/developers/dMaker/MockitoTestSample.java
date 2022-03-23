package com.developers.dMaker;

import com.developers.dMaker.Type.StatusCode;
import com.developers.dMaker.dto.DeveloperDetailDto;
import com.developers.dMaker.entity.Developer;
import com.developers.dMaker.repository.DeveloperRepository;
import com.developers.dMaker.repository.RetiredDeveloperRepository;
import com.developers.dMaker.service.DMakerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.developers.dMaker.Type.DeveloperLevel.SENIOR;
import static com.developers.dMaker.Type.DeveloperSkillType.FRONT_END;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MockitoTestSample {
    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DMakerService dMakerService;

    // Mockito Example
    @Test
    public void testPractice() {
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerLevel(SENIOR)
                        .developerSkillType(FRONT_END)
                        .experienceYear(12)
                        .statusCode(StatusCode.EMPLOYED)
                        .name("name")
                        .age(12)
                        .build()
                ));

        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("memberId");

        assertEquals(SENIOR, developerDetail.getDeveloperLevel());
        /*
        assertEquals(JUNGNIOR, developerDetail.getDeveloperLevel());
        Expected :JUNGNIOR
        Actual   :SENIOR
         */
        assertEquals(FRONT_END, developerDetail.getDeveloperSkillType());
        assertEquals(12, developerDetail.getAge());
    }
}