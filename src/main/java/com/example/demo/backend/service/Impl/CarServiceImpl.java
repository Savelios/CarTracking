package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.service.CarService;
import com.example.demo.backend.viewModel.CarViewModel;

import java.util.ArrayList;
import java.util.List;
public class CarServiceImpl implements CarService {
    CarRepository carRepository;
    @Override
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }
    @Override
    public List<CarViewModel> getAll() {
        List<Car> carList = carRepository.findAll();
        List<CarViewModel> carViewModels = new ArrayList<>();
        for (Car item : carList) {
            CarViewModel carViewModel = new CarViewModel();
            carViewModel.setId(item.getId());
            carViewModel.setBrand(item.getBrand());
            carViewModel.setModel(item.getModel());
            carViewModel.setRegistrationNumber(item.getRegistrationNumber());
            carViewModels.add(carViewModel);
        }
        return carViewModels;
    }
}