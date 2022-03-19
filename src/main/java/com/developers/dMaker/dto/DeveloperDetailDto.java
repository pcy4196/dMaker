package com.developers.dMaker.dto;

import com.developers.dMaker.Type.DeveloperLevel;
import com.developers.dMaker.Type.DeveloperSkillType;
import com.developers.dMaker.Type.StatusCode;
import com.developers.dMaker.entity.Developer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {

    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYear;
    private String memberId;
    private String name;
    private Integer age;
    private StatusCode statusCode;

    public static DeveloperDetailDto fromEntity(Developer developer) {
        return DeveloperDetailDto.builder()
                .developerSkillType(developer.getDeveloperSkillType())
                .developerLevel(developer.getDeveloperLevel())
                .experienceYear(developer.getExperienceYear())
                .memberId(developer.getMemberId())
                .statusCode(developer.getStatusCode())
                .name(developer.getName())
                .age(developer.getAge())
                .build();
    }
}
