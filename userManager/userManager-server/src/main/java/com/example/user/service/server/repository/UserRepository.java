package com.example.user.service.server.repository;

import com.example.user.service.server.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(String userId);
}
