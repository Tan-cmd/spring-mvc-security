package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    //home page
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    //add a request mapping for leaders
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    //add a request mapping for admins
    @GetMapping("/systems")
    public String showAdmins() {
        return "admins";
    }
}
