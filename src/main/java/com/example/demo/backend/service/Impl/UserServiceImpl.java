package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.User;
import com.example.demo.backend.repository.UserRepository;
import com.example.demo.backend.service.UserService;
import com.example.demo.backend.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User create(User user) {

        userRepository.save(user);
        return user;
    }
    @Override
    public List<UserViewModel> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (User item : userList) {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(item.getId());
            userViewModel.setUsername(item.getUsername());
            userViewModel.setEmail(item.getEmail());
            userViewModels.add(userViewModel);
        }
        return userViewModels;
    }
}
