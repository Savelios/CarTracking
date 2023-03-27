package com.example.demo.backend.service;

import com.example.demo.backend.domain.User;
import com.example.demo.backend.viewModel.UserViewModel;

import java.util.List;

public interface UserService {
    User create(User request);
    List<UserViewModel> getAll();
}
