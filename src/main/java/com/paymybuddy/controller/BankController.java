package com.paymybuddy.controller;

import com.paymybuddy.dto.BankDto;
import com.paymybuddy.mapper.BankMapper;
import com.paymybuddy.model.Bank;
import com.paymybuddy.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/bankregistration")
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private BankMapper bankMapper;

    @PostMapping
    public String registerBank(@ModelAttribute("bank") BankDto bankDto) {
        //create
        Bank bank = bankMapper.toEntity(bankDto);
        bankService.createAccount(bank);
        return "redirect:/home";
    }
    @ModelAttribute("bank")
    public BankDto bankDto() {
        return new BankDto();
    }

    @GetMapping
    public String showRegistrationBankForm() {
        return "bankregistration";
    }
}
