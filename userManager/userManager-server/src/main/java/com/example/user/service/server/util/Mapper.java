package com.example.user.service.server.util;

import com.example.user.manager.datatypes.UserResponse;
import com.example.user.service.server.entity.User;

public class Mapper {

    public static UserResponse mapToResponse(User user) {
        if(user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setGender(user.getGender());
        userResponse.setUserState(user.getUserState());
        userResponse.setUserId(user.getUserId());

        return userResponse;
    }
}
