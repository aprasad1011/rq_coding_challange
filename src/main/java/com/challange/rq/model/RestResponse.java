package com.challange.rq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestResponse<T> {

    private String status;
    private T data;
    private String message;
}
