package com.developers.dMaker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor          // 기본생성자
@AllArgsConstructor         // 모든 파라미터 생성자
@RequiredArgsConstructor    // 필수 파라미터(@NonNull) 생성자
public class DevDto {
    @NonNull
    String firstName;
    Integer age;
    LocalDateTime startAt;
}
