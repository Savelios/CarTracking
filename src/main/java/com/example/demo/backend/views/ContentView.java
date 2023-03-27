package com.example.demo.backend.views;

import com.example.demo.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class ContentView extends Div implements RouterLayout {

    public ContentView() {
        setWidth("100%");
        mainScreen();
    }

    public void mainScreen() {
        Image image = new Image("https://solomonpro.ru/img/main_image_desk.svg", "Main Background");
        this.addClassNames("main-view");
        add(image);
    }
}
