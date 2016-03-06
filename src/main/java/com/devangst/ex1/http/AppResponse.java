package com.devangst.ex1.http;

import org.springframework.http.HttpStatus;

public class AppResponse<T> {
    private final HttpStatus status;
    private final String text;
    private final T body;

    public AppResponse(T body) {
        this(HttpStatus.OK, "OK", body);
    }

    public AppResponse(HttpStatus status, String text, T body) {
        this.status = status;
        this.text = text;
        this.body = body;
    }
}
