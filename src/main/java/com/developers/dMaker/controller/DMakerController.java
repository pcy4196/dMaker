package com.developers.dMaker.controller;

import com.developers.dMaker.Exception.DMakerException;
import com.developers.dMaker.dto.*;
import com.developers.dMaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        return dmakerService.getAllEmplyedDevelopers();
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

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable String memberId
    ) {
        log.info("Delete /deleteDeveloper HTTP/1.1 ");

        return dmakerService.deleteDeveloper(memberId);
    }

    // DmakerController 전용 Exception
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DMakerException.class)
    public DmakerErrorResponse handleException(DMakerException e, HttpServletRequest request) {
        log.error("errorCode : {}, url : {}, message : {}", 
                e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());
        
        return DmakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }
}
