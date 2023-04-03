package com.example.demo.component.view;

import com.example.demo.backend.service.CarService;
import com.example.demo.backend.service.LocationService;
import com.example.demo.backend.viewModel.CarViewModel;
import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@UIScope()
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarRoutesView extends Div {
    private Select<CarViewModel> carSelect;
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
        this.departureDatePicker = createDepartureDatePicker();
        this.returnDatePicker = createReturnDatePicker();
        this.createTrackBtn = createTrackButton();

        //todo change to get cars by user. get user from Authenticated user.
        var cars = carService.getAll();
        state = new CarRouteViewModel(cars);

        addClassNames("carRoutesView");
        add(carSelect, departureDatePicker, returnDatePicker, createTrackBtn);
    }
    private static class CarRouteViewModel {
        final List<CarViewModel> cars;
        LocalDate departureDate;
        LocalDate returnDate;
        CarRouteViewModel(List<CarViewModel> cars) {
            this.cars = cars;
        }
    }
    public void createCarSelect() {
        carSelect = new Select<>();
        carSelect.setLabel("Выберите автомобиль");
        carSelect.setItems(state.cars);
        carSelect.setItemLabelGenerator(car ->
                String.format("%s %s(%s)",car.getBrand(), car.getModel(), car.getRegistrationNumber()));
    }
    private Button createTrackButton(){
        if (this.createTrackBtn != null)
            return this.createTrackBtn;

        Button createBtn = new Button("Создать трек");
        createBtn.addClassNames("createTrackBtn");
        createBtn.addClickListener(e -> {
            var coordinates = locationService
                    .getAllLocations()
                    .stream()
                    .map(l -> new MapJSUtil.Coordinate(l.getLat(), l.getLon()))
                    .toList();
            createCarSelect();
            mapView.addRoute(coordinates);
            createCarSelect();
        });
        return createBtn;
    }
    private DatePicker createDepartureDatePicker(){
        if (this.departureDatePicker != null)
            return this.departureDatePicker;

        DatePicker departureDate = new DatePicker();
        departureDate.setLabel("Начальная дата");
        departureDate.addClassNames("departureDate");
        departureDate.setMax(state.departureDate);
        departureDate.addValueChangeListener((event) -> {
                    if (event.getValue() == null) {
                        return;
                    }
                    state.departureDate = event.getValue();
                });
        return departureDate;
    }
    private DatePicker createReturnDatePicker(){
        if (this.returnDatePicker != null)
            return this.returnDatePicker;

        DatePicker returnDate = new DatePicker();
        returnDate.setLabel("Финальная дата");
        returnDate.addClassNames("returnDate");
        returnDate.setMin(state.returnDate);
        returnDate.addValueChangeListener((event) -> {
                    if (event.getValue() == null) {
                        return;
                    }
                    state.returnDate = event.getValue();
                });
        return returnDate;
    }
}