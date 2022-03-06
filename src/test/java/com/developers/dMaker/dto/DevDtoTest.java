package com.developers.dMaker.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DevDtoTest {

    @Test
    void test() {
        DevDto devDto = new DevDto();

        devDto.setFirstName("snow");
        devDto.setAge(22);
        devDto.setStartAt(LocalDateTime.now());
        System.out.println(devDto);
        // DevDto(firstName=snow, age=22, startAt=2022-03-06T23:50:13.984636)

        // @Builder
        DevDto devDto1 = DevDto.builder()
                .firstName("snow1")
                .age(22)
                .startAt(LocalDateTime.now())
                .build();

        System.out.println(devDto1);
        // DevDto(firstName=snow1, age=22, startAt=2022-03-07T00:00:30.085155)
        devDto1.printLog();
        // 00:06:38.730 [main] INFO com.developers.dMaker.dto.DevDto - snow1
    }
}