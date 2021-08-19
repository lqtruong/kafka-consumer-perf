package com.turong.training.rest.service;

import com.turong.training.rest.entity.User;

public interface UserService {

    User getUser(String id);

    User create(User userToCreate);

    int deleteByEmail(String email);

    int deleteById(String id);

}
