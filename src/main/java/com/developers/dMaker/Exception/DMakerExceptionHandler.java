package com.developers.dMaker.Exception;

import com.developers.dMaker.dto.DmakerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DMakerException.class)
    public DmakerErrorResponse handleException(DMakerException e, HttpServletRequest request) {
        log.error("errorCode : {}, url : {}, message : {}",
                e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return DmakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,   // POST, GET 매핑 오류
            MethodArgumentNotValidException.class           // @Min(0) -- 조건이 맞이 않을 경우
    })
    public DmakerErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error("url : {}, message : {}",
                request.getRequestURI(), e.getMessage());

        return DmakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(DMakerErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public DmakerErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("url : {}, message : {}",
                request.getRequestURI(), e.getMessage());

        return DmakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(DMakerErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
