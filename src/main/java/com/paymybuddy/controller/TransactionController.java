package com.paymybuddy.controller;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.mapper.TransactionMapper;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;
    @Autowired

    private TransactionMapper transactionMapper;

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") TransactionDto transactionDto) throws Exception {
        //create
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transactionService.createTransaction(transaction);
        return "redirect:/transaction";
    }
}
