package com.alexjuca.okta.oktademo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public WeatherToday getWeatherToday(Principal principal) {
        return new WeatherToday("10 C", "22 C", principal.getName());
    }

    public static class WeatherToday {
        private final String lowTemp;
        private final String highTemp;
        private final String username;

        public WeatherToday(String lowTemp, String highTemp, String username) {
            this.lowTemp = lowTemp;
            this.highTemp = highTemp;
            this.username = username;
        }
    }

    @GetMapping("/everyone")
    @PreAuthorize("hasAuthority('Everyone')")
    public String everyoneRole() {
        return "Okta Groups have been mapped to Spring Security authorities correctly!";
    }
}
