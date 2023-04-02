package com.example.demo.backend.service.servant;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.viewModel.CarViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServant {
    private final AuthenticatedUser user;
    CarRepository repository;

    public CarServant(AuthenticatedUser user, CarRepository repository) {
        this.user = user;
        this.repository = repository;
    }

    public void searchCar(String registrationNumber) throws Exception {
        var carOptional = repository.searchByRegistrationNumber(registrationNumber);

        checkRegistrationNumberFormat(registrationNumber);

        if (carOptional.isEmpty())
            throw new Exception("Такого автомобиля нет!");
    }

    public List<CarViewModel> getCars(String brand, String model, String registrationNumber){
        List<Car> carList = repository.findAll();
        List<CarViewModel> carViewModels = new ArrayList<>();
        for (Car item : carList) {
            CarViewModel carViewModel = new CarViewModel();
            carViewModel.setId(item.getId());
            carViewModel.setBrand(item.getBrand());
            carViewModel.setModel(item.getModel());
            carViewModels.add(carViewModel);
        }
        return carViewModels;
    }

    public void createCar(String registrationNumber, String brand, String model) throws Exception {
        if (brand == null || brand.isEmpty()) {
            throw new Exception("Марка не может быть пустой!");
        }
        if (model == null || model.isEmpty()) {
            throw new Exception("Модель не может быть пустой!");
        }
        if (registrationNumber == null || registrationNumber.isEmpty()) {
            throw new Exception("Регистрационный номер не может быть пустым!");
        }
        checkRegistrationNumberFormat(registrationNumber);

        var carOptional = repository.searchByRegistrationNumber(registrationNumber);
        if (carOptional.isPresent()) {
            throw new Exception("Автомобиль с таким регистрационным номером уже существует!");
        }
//        Car car = new Car();
//        car.setRegistrationNumber(registrationNumber);
//        car.setBrand(brand);
//        car.setModel(model);
        Car car = Car.builder()
                .brand(brand)
                .registrationNumber(registrationNumber)
                .model(model)
                .build();
        repository.save(car);
    }

    public void checkRegistrationNumberFormat(String registrationNumber) throws Exception {
        String regex = "^([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]\\s*\\d{3}\\s*[АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{2,3})|([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{3}\\s*\\d{2,3})$";
        if (!registrationNumber.matches(regex)) {
            throw new Exception("Регистрационный номер не соответствует формату: A111AA77(7)");
        }
    }
}