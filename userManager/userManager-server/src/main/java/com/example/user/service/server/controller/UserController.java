package com.example.user.service.server.controller;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.example.user.manager.datatypes.CreateUserRequest;
import com.example.user.manager.datatypes.UserResponse;
import com.example.user.service.server.entity.User;
import com.example.user.service.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "get user with userId", absolute = true)
    @Transactional
    public ResponseEntity getUser(
        @PathVariable(value = "userId") String userId
    ) throws Exception {
        User user = userService.getUser(userId);
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setGender(user.getGender());
        userResponse.setUserId(user.getUserId());

        return new ResponseEntity(userResponse, HttpStatus.OK);

    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "createUser", absolute = true)
    public ResponseEntity createUser(
        @RequestBody @ApiParam(value = "Create user request", required = true)
        @Valid CreateUserRequest createUserRequest
    ) throws Exception {
        User user = userService.createUser(createUserRequest);

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setGender(user.getGender());
        userResponse.setUserId(user.getUserId());

        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}
