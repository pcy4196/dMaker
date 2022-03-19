package com.developers.dMaker.dto;

import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EditDeveloper {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull    // 빈값 허용 X
        private DeveloperLevel developerLevel;

        @NotNull
        private DeveloperSkillType developerSkillType;

        @NotNull
        @Min(0)     // 최소 0
        @Max(20)    // 최대 20
        private Integer experienceYears;
    }
}
