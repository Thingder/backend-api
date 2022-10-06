package com.redcutlery.thingder.exception;

import lombok.AllArgsConstructor;

public class BaseException {

    @AllArgsConstructor
    public static class ServerError extends RuntimeException {
        String message;
    }

    @AllArgsConstructor
    public static class BadRequest extends RuntimeException {
        String message;
    }

    @AllArgsConstructor
    public static class Unauthorized extends RuntimeException {
        String message;
    }

    @AllArgsConstructor
    public static class Forbidden extends RuntimeException {
        String message;
    }

    @AllArgsConstructor
    public static class NotFound extends RuntimeException {
        String message;
    }
}
