package com.paymybuddy.controller;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.dto.CreditDto;
import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.mapper.TransactionMapper;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.ContactService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private TransactionMapper transactionMapper;
    @Autowired
    private ContactService contactService;

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") TransactionDto transactionDto) throws Exception {
        //create
        transactionService.createTransaction(transactionMapper.toEntity(transactionDto));
        return "redirect:/transaction";
    }
    @PostMapping(value = "/crediter")
    public String crediterUserAcount(@ModelAttribute("credit") CreditDto creditDto) throws Exception {
        //create
        transactionService.transfetMoneyFromBank(creditDto.getAmount());
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

        //recuperation liste des contacts
        List<ContactDto> contacts = contactService.listOfContacts(user);
        model.addAttribute("contacts", contacts);

        // liste des transactions
        List<Transaction> transactions = transactionService.findByEmitter(user);
        model.addAttribute("transactions", transactions);

        //recuperation du solde;
        Double userSold = userService.findUser().getSold();
        model.addAttribute("userSold", userSold);


        List<Transaction> listTransactions = new ArrayList<>();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", 0);
        model.addAttribute("totalItems", 0);

        model.addAttribute("transaction", new TransactionDto());
        return "transaction";

    }
    @PutMapping(value = "/transferMoneyToBank", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Transaction transferMoneyToBank(@RequestBody Transaction transaction , Authentication authentication) {
        return transactionService.transferMoneyToBank(transaction, authentication);
    }
}
