package com.example.demo.ui;

import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;

import java.util.Collection;

@JsModule("./src/map/MapHelper.js")
public class OSMMapView extends Div {

    private static final String DEFAULT_IDENTIFIER = "main_map";
    private static final String DEFAULT_VARIABLE_NAME= "mainMapView";
    private final String variableName;
    private final String identifier;

    public OSMMapView() {
        this(DEFAULT_VARIABLE_NAME);
    }
    public OSMMapView(String variableName) {
        this(variableName, DEFAULT_IDENTIFIER);
    }

    public OSMMapView(String variableName, String identifier) {
        this.variableName = variableName;
        this.identifier = identifier;
        this.setId(identifier);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initializeMap();
    }

    private void initializeMap() {
        String script = String.format("createMapView('%s')", this.identifier);
        var executionResult = this.getElement().executeJs(script);
    }

    public void addPolyline(Collection<MapJSUtil.Coordinate> coordinates) {
        final String coordinatesScript = MapJSUtil.coordinatesArrayScript(coordinates);
        final String script = String.format("addRoute(%s)", coordinatesScript);
        var executionResult = this.getElement().executeJs(script);
        System.out.printf("Execution result is %s%n", executionResult.isSentToBrowser());
    }
}
