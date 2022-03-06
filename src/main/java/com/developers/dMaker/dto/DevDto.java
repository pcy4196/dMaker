package com.developers.dMaker.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor          // 기본생성자
@AllArgsConstructor         // 모든 파라미터 생성자
@RequiredArgsConstructor    // 필수 파라미터(@NonNull) 생성자
@Builder
@Slf4j
public class DevDto {
    @NonNull
    String firstName;
    Integer age;
    LocalDateTime startAt;

    public void printLog() {
        log.info(getFirstName());
    }
}
