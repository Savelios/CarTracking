package com.example.demo.component.form;

import com.example.demo.backend.service.Impl.AccountServiceImpl;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.ContentView;
import com.example.demo.backend.views.HeaderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Авторизация")
@Route(value = "auth", layout = ContentView.class)
public class AuthForm extends FormLayout implements BeforeEnterObserver {


    private final TextField loginTF;
    private final PasswordField passwordField;
    private final Button enterButton;
    private final AuthViewModel state = new AuthViewModel();
    private final AccountServiceImpl accountService;
    private final AuthenticatedUser authenticatedUser;

    private final Label label;

    public AuthForm(AccountServiceImpl accountService, AuthenticatedUser authenticatedUser) {

        this.accountService = accountService;
        this.authenticatedUser = authenticatedUser;
        HeaderView headerView= new HeaderView(authenticatedUser);

        this.loginTF = createLoginField();
        this.passwordField = createPasswordField();
        this.enterButton = createEnterButton();
        this.setWidth(String.valueOf(false));
        this.label = createLabel();

        this.setResponsiveSteps(
                new ResponsiveStep("0", 1)
        );


        enterButton.addClickListener(event -> {
            if (authenticatedUser.get().isPresent()) {
                enterButton.getUI().ifPresent(ui ->
                        ui.navigate("map"));
//                headerView.authButton.setText("Выйти");
//                headerView.authButton.addClickListener(event1 -> {authenticatedUser.logout();});
            }
        });


        enterButton.addClassNames("enter-button");
        loginTF.addClassNames("login-txtBox");
        passwordField.addClassNames("password-txtBox");

        addClassNames("authorization-view");

        addFormItem(this.loginTF, "Введите ваш логин");
        addFormItem(this.passwordField, "Введите ваш пароль");


        add(enterButton);

        //addFormItem(this.label, "Авторизованный пользователь");
        //add(label);

    }

    private TextField createLoginField() {
        if (this.loginTF != null)
            return this.loginTF;

        TextField login = new TextField();
        login.setPlaceholder("Логин");
        //login.setPattern("^(?=.{5,32}$)[^@]*@[^@]*$");
        login.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.login = event.getValue();
        });
        return login;
    }

    private PasswordField createPasswordField() {
        if (this.passwordField != null)
            return this.passwordField;

        PasswordField password = new PasswordField();
        password.setPlaceholder("Пароль");
        password.addValueChangeListener((event) -> {
            if (event.getValue() != null) {
                state.password = event.getValue();
            }
        });
        return password;
    }

    private Button createEnterButton() {
        if (this.enterButton != null)
            return this.enterButton;

        Button button = new Button("Войти");
        button.addClickListener((event) -> {
            authenticate();
        });
        return button;
    }

    private Label createLabel() {
        Label label = new Label();
        return label;
    }

    private void authenticate() {
        try {
            accountService.authenticate(state.login, state.password);
            Notification.show("Успешно").open();
        } catch (Exception e) {
            Notification.show(e.getMessage()).open();
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {

            String username = authenticatedUser.get().toString();
            this.label.setText("Вы авторизованы как " + username);
        }
    }

    private static class AuthViewModel {
        String login = "";
        String password = "";
    }
}
