package com.example.session15.controller;

import com.example.session15.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @GetMapping("registerUser")
    public String registerUser( Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @GetMapping("result")
    public String result() {
        return "result";
    }

    @PostMapping("register-save")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerUser";
        }
        return "result";
    }
}
