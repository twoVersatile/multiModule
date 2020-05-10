package com.example.user.service.client;

import com.example.user.manager.datatypes.CreateUserRequest;
import com.example.user.manager.datatypes.UserResponse;
import com.sun.jersey.api.client.GenericType;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserServiceClient extends BaseClient {
    private static final String GET_USER_BY_ID = "/user/%s";
    private static final String CREATE_USER = "/user";

    private String baseUrl;

    public UserServiceClient(ClientConfig clientConfig) {
        super();
        this.baseUrl = "http://" + clientConfig.getHost() + ":" + clientConfig.getPort();
    }

    public UserResponse getUserById(String userId) {
        String url = baseUrl + String.format(GET_USER_BY_ID, userId);

        Map<String, Object> headerMap = new HashMap<>();

        com.sun.jersey.api.client.ClientResponse response = null;
        try {
            response = call(url, MediaType.APPLICATION_JSON_TYPE, CallType.GET, null, headerMap);

            if (response != null && response.hasEntity()) {
                UserResponse userResponse =
                    response.getEntity(UserResponse.class);
                return userResponse;
            } else {
                throw new RuntimeException("Failed to get user Details");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        String url = baseUrl + CREATE_USER;

        Map<String, Object> headerMap = new HashMap<>();

        com.sun.jersey.api.client.ClientResponse response = null;
        try {
            response = call(url, MediaType.APPLICATION_JSON_TYPE, CallType.POST, createUserRequest, headerMap);

            if (response != null && response.hasEntity()) {
                UserResponse userResponse = response.getEntity(new GenericType<UserResponse>() {
                });
                return userResponse;
            } else {
                throw new RuntimeException("Failed to get user Details");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
