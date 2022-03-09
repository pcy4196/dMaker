package com.developers.dMaker.controller;

import com.developers.dMaker.service.DmakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    /*
    DMakerController(Bean) ->  DmakerService(Bean) -> DeveloperRepository(Bean)
    ======= SPRING Application ========
    */

    private final DmakerService dmakerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1 ");

        return Arrays.asList("snow", "elsa", "olaf");
    }

    // TEST를 위한 컨트롤러 메서드 추가 - 실제는 POST로 사용
    @GetMapping("/create-developers")
    public List<String> createdevelopers() {
        log.info("GET /create-developers HTTP/1.1 ");

        dmakerService.createDeveloper();

        return Collections.singletonList("Olaf");
    }
}
