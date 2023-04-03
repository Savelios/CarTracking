package com.example.demo.backend.service;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.viewModel.CarViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CarService {
    Car create(Car car);
    List<CarViewModel> getAll();
}