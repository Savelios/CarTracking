package com.example.demo.component.form;

import com.example.demo.backend.domain.enums.Roles;
import com.example.demo.backend.service.servant.UserServant;
import com.example.demo.backend.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;
@PageTitle("Создание пользователя")
@Route(value = "create-user", layout = ContentView.class)
@AnonymousAllowed
public class CreateUserForm extends FormLayout {
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final TextField emailField;
    private final TextField phoneField;
    private final Button createButton;
    private final UserViewModel state = new UserViewModel();
    private final Select<Roles> selectRoles;
    private final UserServant userServant;
    public CreateUserForm(UserServant userServant) {
        this.userServant = userServant;

        this.usernameField = createUsernameField();
        this.passwordField = createPasswordField();
        this.emailField = createEmailField();
        this.phoneField = createPhoneField();

        this.selectRoles = createSelect();

        this.createButton = createButton();

        this.addClassNames("user-view");
        addFormItem(this.usernameField, "Введите username");
        addFormItem(this.passwordField, "Введите пароль");
        addFormItem(this.emailField, "Введите email");
        addFormItem(this.phoneField, "Введите номер телефона");
        addFormItem(this.selectRoles, "Выберите права доступа:");
        add(this.createButton);
    }
    private Select<Roles> createSelect() {
        if (this.selectRoles != null)
            return this.selectRoles;

        Select<Roles> roles = new Select<>();
        roles.setItemLabelGenerator(Roles::name);

        roles.setItems(Roles.USER, Roles.ADMIN);
        roles.addValueChangeListener(event -> {
           if (event.getValue() == null)
               return;
           this.state.role = event.getValue();
        });
        return roles;
    }
    private TextField createUsernameField() {
        if (this.usernameField != null)
            return this.usernameField;

        TextField username = new TextField();
        username.setPlaceholder("Username");
        username.setMaxLength(10);
        username.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.username = event.getValue();
        });
        return username;
    }
    private PasswordField createPasswordField() {
        if (this.passwordField != null)
            return this.passwordField;

        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");
        password.setMaxLength(32);
        password.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.password = event.getValue();
        });
        return password;
    }

    private TextField createEmailField() {
        if (this.emailField != null)
            return this.emailField;

        TextField email = new TextField();
        email.setPlaceholder("Email");
        email.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.email = event.getValue();
        });
        return email;
    }

    private TextField createPhoneField() {
        if (this.phoneField != null)
            return this.phoneField;

        TextField phone = new TextField();
        phone.setPlaceholder("Phone");
        phone.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.phone = event.getValue();
        });
        return phone;
    }

    private Button createButton() {
        if (this.createButton != null)
            return this.createButton;

        Button button = new Button("Добавить пользователя");
        button.addClickListener((event) -> {
            createUser();
        });
        return button;
    }

    private void createUser() {
        try {
            userServant.createUser(state.username, state.password, state.email, state.phone, state.role);
            Notification.show("Пользователь успешно добавлен.").open();
        } catch (Exception e) {
            Notification.show(e.getMessage()).open();
        }
    }
    private static class UserViewModel {
        String username = "";
        String password = "";
        String email = "";
        String phone = "";
        Roles role = Roles.USER;
    }
}