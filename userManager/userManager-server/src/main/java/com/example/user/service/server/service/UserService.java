package com.example.user.service.server.service;

import com.example.user.manager.datatypes.CreateUserRequest;
import com.example.user.service.server.entity.User;

public interface UserService {
    User getUser(String userId);

    User createUser(CreateUserRequest request);

    User activateUser(String userId);
}
