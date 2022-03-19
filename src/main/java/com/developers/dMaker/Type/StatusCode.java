package com.developers.dMaker.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

    EMPLOYED("재직"),
    RETIRED("퇴사");

    private final String description;
}
