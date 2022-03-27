package com.developers.dMaker.controller;

import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.DeveloperSkillType;
import com.developers.dMaker.dto.DeveloperDto;
import com.developers.dMaker.service.DMakerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// DMakerController 컨트롤러만 로드
@WebMvcTest(DMakerController.class)
class DMakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 가짜 bean 등록
    @MockBean
    private DMakerService dMakerService;

    protected MediaType contentType = new MediaType(APPLICATION_JSON.getType()
                                                    , APPLICATION_JSON.getSubtype()
                                                    , StandardCharsets.UTF_8);

    @Test
    public void getAlldevelopers() throws Exception {
        DeveloperDto juniorDeveloperDto = DeveloperDto.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.BACK_END)
                .memberId("memberId1")
                .build();

        DeveloperDto seniorDeveloperDto = DeveloperDto.builder()
                .developerLevel(DeveloperLevel.SENIOR)
                .developerSkillType(DeveloperSkillType.BACK_END)
                .memberId("memberId2")
                .build();

        // 서비스 호출 시 아래 내용으로 데이터 Return
        given(dMakerService.getAllEmplyedDevelopers())
                .willReturn(Arrays.asList(juniorDeveloperDto, seniorDeveloperDto));

        // developers 호출 시 dMakerService.getAllEmplyedDevelopers() 메서드 호출
        // juniorDeveloperDto, seniorDeveloperDto 리턴 --> mock으로 설정
        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.[0].developerSkillType", is(DeveloperSkillType.BACK_END.name()))
                )
                .andExpect(
                        jsonPath("$.[0].developerLevel", is(DeveloperLevel.JUNIOR.name()))
                )
                .andExpect(
                        jsonPath("$.[0].memberId", is("memberId1"))
                )
                .andExpect(
                        jsonPath("$.[1].memberId", is("memberId2"))
                )
                ;
        /*
        -- developers 호출 시 return 값
        MockHttpServletResponse:
               Status = 200
        Error message = null
              Headers = [Content-Type:"application/json"]
         Content type = application/json
                 Body = [{"developerLevel":"JUNIOR","developerSkillType":"BACK_END","statusCode":null,"memberId":"memberId1"},{"developerLevel":"SENIOR","developerSkillType":"BACK_END","statusCode":null,"memberId":"memberId2"}]
        Forwarded URL = null
       Redirected URL = null
              Cookies = []
         */
    }


}