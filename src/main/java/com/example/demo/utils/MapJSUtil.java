package com.example.demo.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapJSUtil {
    private static final String VARIABLE_NAME  = "<VARIABLE_NAME>";
    private static final String IDENTIFIER     = "<IDENTIFIER>";

    public static String waypoints(String mapId, Collection<Coordinate> points) {
//        "L.Routing.control({\n" +
//                "  waypoints: [\n" +
//                "    L.latLng(57.74, 11.94),\n" +
//                "    L.latLng(57.6792, 11.949)\n" +
//                "  ]\n" +
//                "}).addTo(map);"
        var strTemplate = """
                        var path = L.polyline(%s, {
                                color: 'red',
                                weight: 3,
                                opacity: 0.5
                        }).addTo(%s);
                """;

        String scriptTemplate = """
                    var map = document.getElementById('%s');
                    L.Routing.control(%s).addTo(map)
                """;
        String pointTemplate = "[%s, %s]";
        List<String> formattedPoints = points.stream()
                .map(p -> String.format(strTemplate, p.latitude, p.longitude))
                .toList();
        String pointsTemplate = "[%s]";
        String pointsJs = String.format(
                pointsTemplate,
                String.join(", ", formattedPoints)
        );
        String resultJs = String.format(strTemplate, mapId, pointsJs);
        return resultJs;
    }

    public static String initializeScript(String variableName, String identifier) {


        final String scriptTemplate = """
                var <VARIABLE_NAME>;
                console.log("first map value ", <VARIABLE_NAME>)
                try {
                  var element = document.getElementById('<IDENTIFIER>')
                  console.log("element finded ", element)
                  <VARIABLE_NAME> = L.map(element)
                    .setView([51.505, -0.09], 13);
                  console.log("map created ", <VARIABLE_NAME>)
                  
                  var tile = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: 'Map data &copy; OpenStreetMap contributors'
                  }).addTo(<VARIABLE_NAME>);
                  console.log("new tile added ", tile)
                } catch (e) {
                  console.log("exception from me ", e)
                } finally {
                  console.log("finally")
                }
                """;
        final String withVariables = scriptTemplate.replaceAll(VARIABLE_NAME, variableName);
        final String withIdentifier = withVariables.replaceAll(IDENTIFIER, identifier);

        return withIdentifier;
    }

    public static String addPolylineScript(Collection<Coordinate> coordinates, String variableName) {
        final String COORDINATES = "<COORDINATES>";
        final String scriptTemplate = """
                var mainMapView;
                var pathCoords = <COORDINATES>;
                console.log("path coords is ", pathCoords)
                                
                L.polyline(pathCoords, {
                  color: 'blue',
                  weight: 7,
                  opacity: 1
                }).addTo(<VARIABLE_NAME>);
                console.log("Polyline with coords added to map")
                """;
        final String coordinatesStr = coordinatesArrayScript(coordinates);
        final String withCoordinates = scriptTemplate.replaceAll(COORDINATES, coordinatesStr);
        final String withVariableName = withCoordinates.replaceAll(VARIABLE_NAME, variableName);
        return withVariableName;
    }

    public static String coordinatesArrayScript(Collection<Coordinate> coordinates) {
        final String scriptTemplate = "[%s]";
        final String coordinateTemplate = "[%s, %s]";
        final String coordinatesStr = coordinates.stream()
                .map(coordinate -> String.format(coordinateTemplate, coordinate.latitude, coordinate.longitude))
                .collect(Collectors.joining(", "));
        return String.format(scriptTemplate, coordinatesStr);
    }

    public static class Coordinate {
        public final double latitude;
        public final double longitude;

        public Coordinate() {
            latitude = 0.0;
            longitude = 0.0;
        }
        public Coordinate(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        /**
         * @return
         */
        @Override
        public String toString() {
            return String.format("[lat = %s, lon = %s]", latitude, longitude);
        }
    }
}
