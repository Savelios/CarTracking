package com.example.demo.component.view;

import com.example.demo.backend.domain.User;
import com.example.demo.backend.service.servant.CarServant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Route("/search-car")
@RolesAllowed("ROLE_USER")
public class SearchCarView extends FormLayout {
    private final TextField searchByRegistrationNumberField;
    private final Button searchButton;
    private final CarViewModel state = new CarViewModel();
    private final CarServant adminService;

    public SearchCarView(CarServant adminService) {
        this.adminService = adminService;
        this.searchByRegistrationNumberField = createSearchRegistrationNumberField();
        this.searchButton = searchButton();
        addFormItem(this.searchByRegistrationNumberField, "Поиск по регистрационному номеру автомобиля");
        add(this.searchButton);
    }
    private TextField createSearchRegistrationNumberField() {
        if (this.searchByRegistrationNumberField != null)
            return this.searchByRegistrationNumberField;

        TextField searchByRegistrationNumber = new TextField();
        searchByRegistrationNumber.setPlaceholder("Поиск по гос номеру");
        searchByRegistrationNumber.setMaxLength(9);
        searchByRegistrationNumber.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.searchByRegistrationNumber = event.getValue();
        });
        return searchByRegistrationNumber;
    }
    private Button searchButton() {
        if (this.searchButton != null)
            return this.searchButton;

        Button button = new Button("Найти автомобиль");
        button.addClickListener((event) -> {
            searchCar();
        });
        return button;
    }
    private void searchCar() {
        try {
            adminService.searchCar(state.searchByRegistrationNumber);
            Notification.show("Найден автомобиль по вашему запросу.").open();
        } catch (Exception e) {
            Notification.show(e.getMessage()).open();
        }
    }
    private static class CarViewModel {
        String searchByRegistrationNumber = "";
    }
}