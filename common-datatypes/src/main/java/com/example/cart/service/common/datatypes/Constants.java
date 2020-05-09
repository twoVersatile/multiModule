package com.example.cart.service.common.datatypes;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_FAST = "fast";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String HEALTH_CHECK_URI = "/health";
    public final static String REQUEST_ID_HEADER = "X_REQUEST_ID";
    public static final String X_RESTBUS_MESSAGE_ID = "X_RESTBUS_MESSAGE_ID";
    public final static String REQUESTED_BY_HEADER = "X_REQUESTED_BY";
    public final static String X_BODY = "X_BODY";

    public static final List<String> SWAGGER_URIS = Arrays.asList(
        "/swagger-ui.html",
        "/webjars",
        "/swagger-resources",
        "/v2/api-docs",
        "/configuration/security",
        "/configuration/ui"
    );

    private Constants() {
    }
}
