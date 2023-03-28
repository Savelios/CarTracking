package com.example.demo.utils;

import com.vaadin.flow.dom.Element;
import org.apache.logging.log4j.util.Strings;
import software.xdev.vaadin.maps.leaflet.flow.LMap;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapJSUtil {
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
    public static class Coordinate {
        public double latitude;
        public double longitude;
    }
}
