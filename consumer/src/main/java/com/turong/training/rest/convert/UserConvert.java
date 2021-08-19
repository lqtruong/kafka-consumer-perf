package com.turong.training.rest.convert;

import com.turong.training.rest.controller.UserResponse;
import com.turong.training.rest.controller.UserSaveRequest;
import com.turong.training.rest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConvert {

    User toUser(final UserSaveRequest userSaveRequest);

    UserResponse toResponse(final User user);

}
