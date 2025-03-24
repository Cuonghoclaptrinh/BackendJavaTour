package com.example.test.Mapper;

import com.example.test.dto.Request.PermissionRequest;
import com.example.test.dto.Request.UserCreationRequest;
import com.example.test.dto.Request.UserUpdateRequest;
import com.example.test.dto.reponse.PermissionResponse;
import com.example.test.dto.reponse.UserResponse;
import com.example.test.entity.Permission;
import com.example.test.entity.Role;
import com.example.test.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
