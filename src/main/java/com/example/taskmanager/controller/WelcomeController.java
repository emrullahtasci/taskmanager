package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hediyelik Eşya Butiği Backend Sistemine Hoş Geldiniz! Sistem tıkır tıkır çalışıyor.";
    }
}