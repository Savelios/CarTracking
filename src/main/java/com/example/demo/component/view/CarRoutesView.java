package com.example.demo.component.view;

import com.example.demo.backend.service.CarService;
import com.example.demo.backend.service.LocationService;
import com.example.demo.backend.viewModel.CarViewModel;
import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarRoutesView extends Div {
    private final Select<CarViewModel> carSelect;
    private final DatePicker departureDatePicker;
    private final DatePicker returnDatePicker;
    private final Button createTrackBtn;
    private final MapView mapView;

    private final CarService carService;
    private final LocationService locationService;

    private final CarRouteViewModel state;

    CarRoutesView(MapView mapView,
                  CarService carService,
                  LocationService locationService) {

        this.carService = carService;
        this.mapView = mapView;
        this.locationService = locationService;
        //todo change to get cars by user. get user from Authenticated user.
        var cars = carService.getAll();
        state = new CarRouteViewModel(cars);

        carSelect = new Select<>();
        carSelect.setLabel("Выберите автомобиль");
        carSelect.setItems(state.cars);
        carSelect.setItemLabelGenerator(car ->
                String.format("%s %s(%s)",car.getBrand(), car.getModel(), car.getRegistrationNumber()));

        departureDatePicker = new DatePicker("Начальная дата");
        departureDatePicker.addClassNames("departureDate");
        departureDatePicker.setMax(state.returnDate);
        departureDatePicker
                .addValueChangeListener(e -> state.departureDate = e.getValue());

        returnDatePicker = new DatePicker("Финальная дата");
        returnDatePicker.addClassNames("returnDate");
        returnDatePicker.setMin(state.departureDate);
        returnDatePicker
                .addValueChangeListener(e -> state.returnDate = e.getValue());

        createTrackBtn = new Button("Создать трек");
        createTrackBtn.addClassNames("createTrackBtn");
        createTrackBtn.addClickListener(e -> {
            var coordinates = locationService
                    .getAllLocations()
                    .stream()
                    .map(l -> new MapJSUtil.Coordinate(l.getLat(), l.getLon()))
                    .toList();

            mapView.addRoute(coordinates);
        });

        this.add(carSelect, departureDatePicker, returnDatePicker, createTrackBtn);
    }

    private static class CarRouteViewModel {
        final List<CarViewModel> cars;
        LocalDate departureDate;
        LocalDate returnDate;
        CarRouteViewModel(List<CarViewModel> cars) {
            this.cars = cars;
        }
    }
}