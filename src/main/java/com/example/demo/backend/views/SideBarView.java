package com.example.demo.backend.views;

import com.example.demo.models.MessageType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

import java.util.Arrays;

public class SideBarView extends HorizontalLayout {
    public  Div usersInfoContainer;
    public  Div carsInfoContainer;
    public Div tracksInfoContainer;
    public SideBarView() {
        screen();
    }
    public void screen() {
        Button showButton = new Button("Показать");
        showButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        Button createButton = new Button("Создать");
        createButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        showButton.addClassNames("show-button");
        createButton.addClassNames("create-button");

        usersInfoContainer = new Div();
        Label nameLabel = new Label("Имя");
        Label showNameLabel = new Label("getName");
        Label surnameLabel = new Label("Фамилия");
        Label showSurnameLabel = new Label("GetLastname");
        Label carLabel = new Label("Транспорт");
        Label showCarLabel = new Label("GetCar");
        Label registrationNumberLabel = new Label("ГОС-НОМЕР");
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

        ListBox usersList = new ListBox();
        usersList.addClassNames("usersList");
        usersList.add(usersInfoContainer);

        this.addClassNames("sideBar");
        this.add(showButton, createButton, usersList);
    }
}
