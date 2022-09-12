package com.paymybuddy.controller;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.mapper.AccountMapper;
import com.paymybuddy.model.Account;
import com.paymybuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;


    @PostMapping
    public String registerUserAccount(@ModelAttribute("account") AccountDto accountDto) {
        // create
        Account account = accountMapper.toEntity(accountDto) ;
        account.setUserAccount(true);
        accountService.createAccount(account);
        return "redirect:/home";
    }
}
