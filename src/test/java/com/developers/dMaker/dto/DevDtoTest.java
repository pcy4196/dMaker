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
    }
}