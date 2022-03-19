package com.developers.dMaker.controller;

import com.developers.dMaker.dto.CreateDeveloper;
import com.developers.dMaker.dto.DeveloperDetailDto;
import com.developers.dMaker.dto.DeveloperDto;
import com.developers.dMaker.dto.EditDeveloper;
import com.developers.dMaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    /*
    DMakerController(Bean) ->  DMakerService(Bean) -> DeveloperRepository(Bean)
    ======= SPRING Application ========
    */

    private final DMakerService dmakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /getAllDevelopers HTTP/1.1 ");

        return dmakerService.getAllDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable String memberId
    ) {
        log.info("GET /getDeveloperDetail HTTP/1.1 ");

        return dmakerService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request
    ) {
        log.info("POST /create-developers HTTP/1.1 ");
        log.info("requset : {}", request);

        return dmakerService.createDeveloper(request);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request
    ) {
        log.info("PUT /editDeveloper HTTP/1.1 ");

        return dmakerService.editDeveloper(memberId, request);
    }
}
