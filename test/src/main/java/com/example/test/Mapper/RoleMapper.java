package com.example.test.Mapper;

import com.example.test.dto.Request.PermissionRequest;
import com.example.test.dto.Request.RoleRequest;
import com.example.test.dto.reponse.PermissionResponse;
import com.example.test.dto.reponse.RoleResponse;
import com.example.test.entity.Permission;
import com.example.test.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions" , ignore = true)
    Role toRole(RoleRequest request);

//    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "mapPermissions")
//    RoleResponse toRoleResponse(Role role);
//
//    @Named("mapPermissions")
//    default Set<PermissionResponse> mapPermissions(Set<Permission> permissions) {
//        if (permissions == null) return null;
//        return permissions.stream()
//                .map(permission -> new PermissionResponse(permission.getName(), permission.getDescription()))
//                .collect(Collectors.toSet());
//    }
}
