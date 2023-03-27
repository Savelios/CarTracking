package com.example.demo.backend.viewModel;

import com.example.demo.backend.domain.PersistentObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel extends PersistentObject {
    String username;
    String email;
    String phone;
    String password;
    Integer roleId;
}
