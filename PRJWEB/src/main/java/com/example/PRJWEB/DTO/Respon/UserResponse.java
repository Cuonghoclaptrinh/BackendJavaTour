package com.example.PRJWEB.DTO.Respon;

import com.example.PRJWEB.Enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    Integer id;
    String username;
    String email;
    Roles roles;
}
