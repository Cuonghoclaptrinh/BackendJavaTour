package com.example.PRJWEB.Mapper;

import com.example.PRJWEB.DTO.Request.UserRequest;
import com.example.PRJWEB.DTO.Respon.UserResponse;
import com.example.PRJWEB.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserRequest(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
