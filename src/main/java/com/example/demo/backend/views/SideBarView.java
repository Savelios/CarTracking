package com.example.demo.backend.views;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.service.CarService;
import com.example.demo.component.view.CarRoutesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@UIScope
public class SideBarView extends HorizontalLayout {
    public  Div usersInfoContainer;
    public  Div carsInfoContainer;
    public Div tracksInfoContainer;

    public SideBarView(CarRoutesView carRoutesView) {
        screen();
        carsInfoContainer();
        this.add(carRoutesView);
    }
    public void screen() {
//        Div btnsDiv = new Div();
//        Button createButton = new Button("Создать");
//        createButton.addClickListener(event -> {
//           enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
//        });
//        createButton.addClassNames("create-button");
//        btnsDiv.addClassNames("btnsDiv");
//        btnsDiv.add(createButton);
//
//        this.addClassNames("sideBar");
//        this.add(btnsDiv);
    }
    public void usersInfoContainer() {
        usersInfoContainer = new Div();
        Label nameLabel = new Label("Имя:");
        Label showNameLabel = new Label("getName");
        Label surnameLabel = new Label("Фамилия:");
        Label showSurnameLabel = new Label("GetLastname");
        Label carLabel = new Label("Транспорт:");
        Label showCarLabel = new Label("GetCar");
        Label registrationNumberLabel = new Label("ГОС-НОМЕР:");
        Label showRegistrationNumberLabel = new Label("GetRegistrationNumber");

        nameLabel.addClassNames("nameLabel", "j");
        showNameLabel.addClassNames("showNameLabel");
        surnameLabel.addClassNames("surnameLabel", "j");
        showSurnameLabel.addClassNames("showSurnameLabel");
        carLabel.addClassNames("carLabel", "j");
        showCarLabel.addClassNames("showCarLabel");
        registrationNumberLabel.addClassNames("registrationNumberLabel", "j");
        showRegistrationNumberLabel.addClassNames("showRegistrationNumberLabel");

        usersInfoContainer.addClassNames("usersInfoContainer");
        usersInfoContainer.add(
                nameLabel,showNameLabel,
                surnameLabel,showSurnameLabel,
                carLabel,showCarLabel,
                registrationNumberLabel,showRegistrationNumberLabel);

        ListBox usersListBox = new ListBox();
        usersListBox.addClassNames("usersList");
        usersListBox.add(usersInfoContainer);

        this.add(usersListBox);
    }
    public void carsInfoContainer() {
        carsInfoContainer = new Div();
        Button createButton = new Button("Создать");
        createButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        createButton.addClassNames("create-button");

        Label brandLabel = new Label("Марка:");
        Label showBrandLabel = new Label("getBrand");
        Label modelLabel = new Label("Модель:");
        Label showModelLabel = new Label("GetModel");
        Label registrationNumberLabel = new Label("ГОС-НОМЕР:");
        Label showRegistrationNumberLabel = new Label("GetRegistrationNumber");

        brandLabel.addClassNames("brandLabel", "j");
        showBrandLabel.addClassNames("showBrandLabel");
        modelLabel.addClassNames("modelLabel", "j");
        showModelLabel.addClassNames("showModelLabel");
        registrationNumberLabel.addClassNames("registrationNumberLabel", "j");
        showRegistrationNumberLabel.addClassNames("showRegistrationNumberLabel");

        carsInfoContainer.addClassNames("carsInfoContainer");
        carsInfoContainer.add(
                brandLabel,showBrandLabel,
                modelLabel,showModelLabel,
                registrationNumberLabel,showRegistrationNumberLabel, createButton);

        ListBox carsListBox = new ListBox();
        carsListBox.addClassNames("carsList");
        carsListBox.add(carsInfoContainer);
        this.add(carsListBox);
    }
//    public void tracksInfoContainer() {
//        tracksInfoContainer = new Div();
//        tracksInfoContainer.addClassNames("tracksInfoContainer");
//        Label carLabel = new Label("Выберите автомобиль");
//        carLabel.addClassNames("carLabel");
//
//        Select<Car> carSelect = new Select<>();
//        carSelect.addClassNames("carSelect");
//
//        List<Car> carList = new ArrayList<>();
//        carSelect.setItems(carList);
//        carSelect.setItemLabelGenerator(car ->
//                String.format("%s %s(%s)",car.getBrand(), car.getModel(), car.getRegistrationNumber()));
//
//        DatePicker departureDate = new DatePicker("Начальная дата");
//        departureDate.addClassNames("departureDate");
//        DatePicker returnDate = new DatePicker("Финальная дата");
//        returnDate.addClassNames("returnDate");
//        departureDate
//                .addValueChangeListener(e -> returnDate.setMin(e.getValue()));
//        returnDate.addValueChangeListener(
//                e -> departureDate.setMax(e.getValue()));
//
//        Button createTrackBtn = new Button("Создать трек");
//        createTrackBtn.addClassNames("createTrackBtn");
//
//        tracksInfoContainer.add(carLabel,carSelect, departureDate, returnDate, createTrackBtn);
//
//        this.add(tracksInfoContainer);
//    }
}