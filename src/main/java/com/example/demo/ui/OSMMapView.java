package com.example.demo.ui;

import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;

import java.util.Collection;

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

        initializeMap();
    }

    private void initializeMap() {
        final String script = MapJSUtil.initializeScript(this.variableName, this.identifier);
        var executionResult = UI.getCurrent().getPage().executeJs(script);
        System.out.printf("Execution result is %s%n", executionResult.isSentToBrowser());
    }

    public void addPolyline(Collection<MapJSUtil.Coordinate> coordinates) {
//        final String initScript = MapJSUtil.initializeScript(this.variableName, this.identifier);
        final String script = MapJSUtil.addPolylineScript(coordinates, this.variableName);
//        final String template = """
//                %s
//
//                %s
//                """;
//        final String resultScript = String.format(template, initScript, script);
        var executionResult = UI.getCurrent().getPage().executeJs(script);
        System.out.printf("Execution result is %s%n", executionResult.isSentToBrowser());
    }

}
