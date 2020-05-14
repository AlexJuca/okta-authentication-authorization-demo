package com.alexjuca.okta.oktademo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public WeatherToday getWeatherToday(@AuthenticationPrincipal OidcUser user) {
        return new WeatherToday("10 C", "22 C", user.getFullName());
    }

    public static class WeatherToday {
        private String lowTemp;
        private String highTemp;
        private String username;

        public WeatherToday() {}

        public WeatherToday(String lowTemp, String highTemp, String username) {
            this.lowTemp = lowTemp;
            this.highTemp = highTemp;
            this.username = username;
        }

        public String getLowTemp() {
            return lowTemp;
        }

        public void setLowTemp(String lowTemp) {
            this.lowTemp = lowTemp;
        }

        public String getHighTemp() {
            return highTemp;
        }

        public void setHighTemp(String highTemp) {
            this.highTemp = highTemp;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    @GetMapping("/everyone")
    @PreAuthorize("hasAuthority('Everyone')")
    public String everyoneRole() {
        return "Okta Groups have been mapped to Spring Security authorities correctly!";
    }
}
