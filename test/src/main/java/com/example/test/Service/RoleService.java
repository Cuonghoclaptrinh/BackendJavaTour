package com.example.test.Service;

import com.example.test.Mapper.RoleMapper;
import com.example.test.Repository.PermissionRepository;
import com.example.test.Repository.RoleRepository;
import com.example.test.dto.Request.RoleRequest;
import com.example.test.dto.reponse.PermissionResponse;
import com.example.test.dto.reponse.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions =permissionRepository.findAllById(request.getPermissions());
        log.info("Permissions fetched: {}", permissions);

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        log.info("Role entity before mapping: {}", role);
        log.info("Permissions in Role entity: {}", role.getPermissions());
        var response =  roleMapper.toRoleResponse(role);
        log.info("RoleResponse: {}", response);
        return response;
    }

    public List<RoleResponse> getAll(){
        var roles =roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void  delete(String role){
        roleRepository.deleteById(role);
    }
}
