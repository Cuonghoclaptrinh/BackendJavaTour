package com.example.test.Service;

import com.example.test.Enums.Roles;
import com.example.test.Exception.AppException;
import com.example.test.Exception.ErrorCode;
import com.example.test.Mapper.UserMapper;
import com.example.test.Repository.RoleRepository;
import com.example.test.Repository.UserRepository;
import com.example.test.config.SecurityConfig;
import com.example.test.dto.Request.UserCreationRequest;
import com.example.test.dto.Request.UserUpdateRequest;
import com.example.test.dto.reponse.UserResponse;
import com.example.test.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Roles.USER.name());
//        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser() {
        log.info("in method get user");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
//    @PostAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id){
        log.info("in method get user by ID");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->new RuntimeException("user not found")));
    }

    public UserResponse  updateUser(String userID, UserUpdateRequest request){
        User user = userRepository.findById(userID).orElseThrow(() ->new RuntimeException("user not found"));
        userMapper.updateUser(user , request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));

    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public UserResponse getMyIno(){
        var context = SecurityContextHolder.getContext();
        String name=context.getAuthentication().getName();


        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
