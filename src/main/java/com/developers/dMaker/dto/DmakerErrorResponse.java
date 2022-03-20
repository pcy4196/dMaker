package com.developers.dMaker.dto;

import com.developers.dMaker.Exception.DMakerErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DmakerErrorResponse {
    private DMakerErrorCode errorCode;
    private String errorMessage;
}
