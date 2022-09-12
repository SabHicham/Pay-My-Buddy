package com.paymybuddy.controller;

import com.paymybuddy.service.UserService;
import com.paymybuddy.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



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
        return new UserDto(userDto().getId(), userDto().getFirstName(), userDto().getLastName(), userDto().getEmail(), userDto().getPassword());
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
