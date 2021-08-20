package com.turong.training.rest.convert;

import com.turong.training.rest.controller.user.UserResponse;
import com.turong.training.rest.controller.user.UserSaveRequest;
import com.turong.training.rest.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

    User toUser(final UserSaveRequest userSaveRequest);

    UserResponse toResponse(final User user);

}
