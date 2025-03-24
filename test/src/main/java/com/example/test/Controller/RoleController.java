package com.example.test.Controller;

import com.example.test.Service.PermissionService;
import com.example.test.Service.RoleService;
import com.example.test.dto.Request.ApiResponse;
import com.example.test.dto.Request.PermissionRequest;
import com.example.test.dto.Request.RoleRequest;
import com.example.test.dto.reponse.PermissionResponse;
import com.example.test.dto.reponse.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build( );
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);

        return ApiResponse.<Void>builder().build();
    }

}
