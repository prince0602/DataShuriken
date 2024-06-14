package com.example.SupaDataShuriken.controller;

import com.example.SupaDataShuriken.service.MapService;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapsController {

    double latitude;
    double longitude;

    @Autowired
    MapService mapService;
    @GetMapping("/map")
    public String getMap(@RequestParam String location, Model model){
        try {
            GeocodingResult[] results = mapService.getCoordinates(location);
            if (results != null && results.length > 0) {
                latitude = results[0].geometry.location.lat;
                longitude = results[0].geometry.location.lng;
                model.addAttribute("latitude", latitude);
                model.addAttribute("longitude", longitude);
                model.addAttribute("location", location);
            } else {
                model.addAttribute("error", "Location not found.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching location.");
        }

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Map</title>\n" +
                "    <script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyA2D9_2UBiSSZvvO4VlQo8OVKojvQwN-Ok\"></script>\n" +
                "    <script>\n" +
                "        function initMap() {\n" +
                "            var location = {lat: " + latitude + ", lng: " + longitude + "};\n" +
                "            var map = new google.maps.Map(document.getElementById('map'), {\n" +
                "                zoom: 8,\n" +
                "                center: location\n" +
                "            });\n" +
                "            var marker = new google.maps.Marker({\n" +
                "                position: location,\n" +
                "                map: map\n" +
                "            });\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body onload=\"initMap()\">\n" +
                "<h1>Map Location: " + location + "</h1>\n" +
                "<div id=\"map\" style=\"height: 500px; width: 100%;\"></div>\n" +
                "<div th:if=\"${error}\">\n" +
                "    <p th:text=\"${error}\" style=\"color: red;\"></p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        return htmlContent;}
}

