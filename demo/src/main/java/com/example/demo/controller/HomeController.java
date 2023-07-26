package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/home")
    public String showHome(Model model) {
        return "home";
    }
}