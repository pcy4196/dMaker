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
public class DeveloperDto {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private StatusCode statusCode;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .statusCode(developer.getStatusCode())
                .memberId(developer.getMemberId())
                .build();
    }
}
