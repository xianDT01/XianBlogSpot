package com.example.BlogSpot.XianBlogSpot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register/register";
    }
}
