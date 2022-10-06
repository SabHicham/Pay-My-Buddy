package com.paymybuddy.controller;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.mapper.AccountMapper;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankService;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankService bankService;
    @Autowired
    private UserService userService;


    @PostMapping
    public String registerUserAccount(@ModelAttribute("account") AccountDto accountDto) {
        // create
        Account account = accountMapper.toEntity(accountDto);
        accountService.createAccount(account);
        return "redirect:/home";
    }

    @ModelAttribute("account")
    public AccountDto accountDto() {
        return new AccountDto();
    }

    @GetMapping()
    public String showRegistrationAccountForm(Model model) {
        // list of banks
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        //model.addAttribute("bank", new Bank());

        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter", user);

        return "account";
    }
}