package com.example.test.Mapper;

import com.example.test.dto.Request.UserCreationRequest;
import com.example.test.dto.Request.UserUpdateRequest;
import com.example.test.dto.reponse.UserResponse;
import com.example.test.entity.Role;
import com.example.test.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//
//    User toUser(UserCreationRequest request);
//
//    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToStrings")
//    UserResponse toUserResponse(User user);
//
//    @Mapping(target = "roles", ignore = true) // Không cập nhật roles khi update
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//
//    @Named("mapRolesToStrings")
//    default Set<String> mapRolesToStrings(Set<Role> roles) {
//        if (roles == null) {
//            return null;
//        }
//        return roles.stream()
//                .map(Role::getName) // Lấy tên của từng Role
//                .collect(Collectors.toSet());
//    }
}
