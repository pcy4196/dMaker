package com.developers.dMaker;

import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.DeveloperSkillType;
import com.developers.dMaker.dto.CreateDeveloper;
import com.developers.dMaker.dto.DeveloperDto;
import com.developers.dMaker.service.DMakerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// spring 서버 실행하는 것과 비슷한 환경을 만들어주는 어노테이션
@SpringBootTest
class JunitTestSample {

    @Autowired
    private DMakerService dMakerService;

    // Junit Example
    @Test
    public void testPractice() {
        dMakerService.createDeveloper(
                CreateDeveloper.Request.builder()
                        .developerLevel(DeveloperLevel.SENIOR)
                        .developerSkillType(DeveloperSkillType.BACK_END)
                        .experienceYears(12)
                        .memberId("memberId")
                        .name("name")
                        .age(32)
                        .build()
        );
        List<DeveloperDto> developerDtoList = dMakerService.getAllEmplyedDevelopers();
        System.out.println("=============================");
        System.out.println(developerDtoList);
        System.out.println("=============================");
    }
}