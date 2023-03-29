package com.example.demo.component.form;

import com.example.demo.backend.viewModel.MessageViewModel;
import com.example.demo.backend.views.ContentView;
import com.example.demo.models.MessageType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

import java.util.Arrays;


@Route(value = "message", layout = ContentView.class)
//@RolesAllowed("ROLE_USER")
@AnonymousAllowed
public class MessageForm extends Div {
    private final MessageViewModel state = new MessageViewModel();
    public MessageForm() {
        screen();
    }
    public void screen() {

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Тип сообщения");
        comboBox.addClassNames("login-txtBox-message");

        comboBox.setAllowCustomValue(true);
        comboBox.setItems(Arrays.stream(MessageType.values()).map(MessageType::getRusName).toList());


        int charLimit = 600;
        Button enterButton = new Button("Отправить");
        TextArea textArea = new TextArea();
        textArea.setWidthFull();
        textArea.setLabel("Описание");
        textArea.setMaxLength(charLimit);
        textArea.addClassNames("password-txtBox-message");
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        String loremIpsum = textArea.getValue();
        textArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });
        textArea.setValue(loremIpsum);

        enterButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });

        enterButton.addClassNames("enter-button-message");
        addClassNames("message-view");
        add(comboBox, textArea, enterButton);
    }
}
