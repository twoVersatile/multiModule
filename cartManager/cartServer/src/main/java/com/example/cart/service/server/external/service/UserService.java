package com.example.cart.service.server.external.service;

import com.example.cart.service.common.datatypes.enums.Gender;
import com.example.user.manager.datatypes.CreateUserRequest;
import com.example.user.manager.datatypes.UserResponse;
import com.example.user.service.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserService {

    @Autowired
    private UserServiceClient userServiceClient;

    public UserResponse getUser(String userId) {
        UserResponse userResponse = userServiceClient.getUserById(userId);

        return userResponse;
    }

    //This method will not be used in this service
    public UserResponse createUser(String name, String userId, Long age, Gender gender) {
        CreateUserRequest createUserRequest = new CreateUserRequest();

        createUserRequest.setAge(age);
        createUserRequest.setGender(gender);
        createUserRequest.setName(name);
        createUserRequest.setUserId(userId);

        UserResponse userResponse = userServiceClient.createUser(createUserRequest);

        return userResponse;
    }

    public UserResponse activateUser(String userId) throws Exception {

        UserResponse userResponse = userServiceClient.activateUser(userId);

        return userResponse;

    }


}
