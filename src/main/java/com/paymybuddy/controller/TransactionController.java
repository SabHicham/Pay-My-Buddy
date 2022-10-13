package com.paymybuddy.controller;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.mapper.TransactionMapper;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.ContactService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ContactService contactService;

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") TransactionDto transactionDto) throws Exception {
        //create
        transactionService.createTransaction(transactionMapper.toEntity(transactionDto));
        return "redirect:/transaction";
    }
    @GetMapping
    public String showTransferForm(HttpServletRequest request, Model model) {
        int pageSize = 5;
        int pageNo = 0;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            pageNo = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            pageSize = Integer.parseInt(request.getParameter("size"));
        }


        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter1", user);

        // List of accounts
        List<Account> accounts = accountService.listOfAccounts(user);
        model.addAttribute("accounts", accounts);

        // List of user accounts
        List<Account> userAccounts = accountService.listOfUserAccounts();
        model.addAttribute("useraccounts", userAccounts);

        //recuperation liste des contacts
        List<Contact> contacts = contactService.listOfContacts(user);
        model.addAttribute("contacts", contacts);

        // liste des transactions
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);

        //Pagination
        /*Page<Transaction> page1 = transactionService.pagination(user, pageNo, pageSize);
        List<Transaction> listTransactions = page1.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page1.getTotalPages());
        model.addAttribute("totalItems", page1.getTotalElements());
        model.addAttribute("transactions", listTransactions);
        model.addAttribute("transaction", new TransactionDto());*/
        List<Transaction> listTransactions = new ArrayList<>();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", 0);
        model.addAttribute("totalItems", 0);

        model.addAttribute("transaction", new TransactionDto());
        return "transaction";

    }
}
