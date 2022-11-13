package com.paymybuddy.controller;

import com.paymybuddy.service.UserService;
import com.paymybuddy.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/registration")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {


        try {
            userService.save(registrationDto);
            return "redirect:/registration?success";
        } catch (Exception e) {
            return "registration-error";

        }
    }

    @PatchMapping
    public String updateUserAccount(@ModelAttribute("user") UserDto userDto) {
        if (userDto.getIban() != null && !userDto.getIban().equals("")) {
            userService.updateIbanUser(userDto.getIban());
            return "redirect:/home";
        }
        return "redirect:/registrationIban";
    }
}