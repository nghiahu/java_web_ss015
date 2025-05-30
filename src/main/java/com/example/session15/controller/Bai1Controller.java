package com.example.session15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Bai1Controller {

    @GetMapping("Bai1")
    public String Welcome() {
        return "Bai1";
    }
}
