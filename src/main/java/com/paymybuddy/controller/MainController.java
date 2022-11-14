package com.paymybuddy.controller;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        User user = userService.findUser();
        Double userSold = user.getSold();
        model.addAttribute("userSold",String.format("%,.2f",userSold));
        if (user.getIban() == null || user.getIban().isEmpty()){
            return "redirect:/registrationIban";
        }
        return "home" ;
    }

    @GetMapping("/registrationIban")
    public String registrationIban(){
        return "registrationIban";
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }


    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        User user = userService.findUser();
        model.addAttribute("contacts", user.getFriends());
        //recuperation du solde;
        Double userSold = userService.findUser().getSold();
        model.addAttribute("userSold", String.format("%,.2f",userSold));
        return "profile" ;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/access-denied";
    }

    @GetMapping("logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
