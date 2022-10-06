package com.paymybuddy.controller;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.mapper.AccountMapper;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankService;
import com.paymybuddy.service.ContactService;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private BankService bankService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContactService contactService;

    @PostMapping
    public String registerContact(@ModelAttribute("contact") UserDto userDto) {
        //create
        userService.createUser(userMapper.toEntity(userDto));
        return "redirect:/transaction";
    }
    @ModelAttribute("contact")
    public AccountDto accountDto() {
        return new AccountDto();
    }

    @GetMapping
    public String showContactForm(Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        //model.addAttribute("bank", new Bank());

        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter", user);

        //recuperation liste des contacts
        List<Contact> contacts = contactService.listOfContacts(user);
        model.addAttribute("contacts", contacts);

        return "contact";
    }

}
