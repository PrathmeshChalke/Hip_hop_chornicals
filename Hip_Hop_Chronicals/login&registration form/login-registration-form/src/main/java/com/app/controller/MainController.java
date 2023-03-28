package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mainPage")
    public String link() {
        return "mainPage";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/cards")
    public String innerpage() {
        return "cards";
    }

}
