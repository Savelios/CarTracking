package com.example.demo.component.form;

import com.example.demo.backend.service.servant.CarServant;
import com.example.demo.backend.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Создание автомобиля")
@Route(value = "create-car", layout = ContentView.class)
//@RolesAllowed("ROLE_ADMIN")
@AnonymousAllowed
public class CreateCarForm extends FormLayout {
    private final TextField brandField;
    private final TextField modelField;
    private final TextField registrationNumberField;
    private final Button createButton;
    private final CarViewModel state = new CarViewModel();
    private final CarServant carServant;

    public CreateCarForm(CarServant carServant) {
        this.carServant = carServant;
        this.brandField = createBrandField();
        this.modelField = createModelField();
        this.registrationNumberField = createRegistrationNumberField();
        this.createButton = createButton();
        addFormItem(this.brandField, "Введите марку автомобиля");
        addFormItem(this.modelField, "Введите модель автомобиля");
        addFormItem(this.registrationNumberField, "Введите регистрационный номер автомобиля");
        this.addClassNames("car-view");

        brandField.addClassNames("brandField");
        modelField.addClassNames("modelField");
        registrationNumberField.addClassNames("registrationNumberField");
        createButton.addClassNames("enter-button");
        add(createButton);
    }
    private TextField createModelField() {
        if (this.modelField != null)
            return this.modelField;

        TextField model = new TextField();
        model.setPlaceholder("Модель");
        model.setMaxLength(10);
        model.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.model = event.getValue();
        });
        this.addClassNames("main-view");
        return model;
    }
    private TextField createBrandField() {
        if (this.brandField != null)
            return this.brandField;

        TextField brand = new TextField();
        brand.setPlaceholder("Марка");
        brand.setMaxLength(10);
        brand.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.brand = event.getValue();
        });
        return brand;
    }
    private TextField createRegistrationNumberField() {
        if (this.registrationNumberField != null)
            return this.registrationNumberField;

        TextField registrationNumber = new TextField();
        registrationNumber.setPlaceholder("Гос номер");
        registrationNumber.setMaxLength(9);
        registrationNumber.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.registrationNumber = event.getValue();
        });
        return registrationNumber;
    }
    private Button createButton() {
        if (this.createButton != null)
            return this.createButton;

        Button button = new Button("Добавить автомобиль");
        button.addClickListener((event) -> {
            createCar();
        });
        return button;
    }
    private void createCar() {
        try {
            carServant.createCar(state.registrationNumber, state.brand, state.model);
            Notification.show("Автомобиль успешно добавлен.").open();
        } catch (Exception e) {
            Notification.show(e.getMessage()).open();
        }
    }
    private static class CarViewModel {
        String registrationNumber = "";
        String brand = "";
        String model = "";
    }
}