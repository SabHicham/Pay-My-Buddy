package com.paymybuddy.service.impl;

import com.paymybuddy.model.Account;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Transaction createTransaction(Transaction transaction) throws Exception {
            User user = userRepository.findByEmail(transaction.getEmitterEmail());
            User receiver = userRepository.findByEmail(transaction.getReceiverEmail());
            transaction.setEmitter(user);
            transaction.setReceiver(receiver);
            if (receiver.getEmail() == user.getEmail()) {
                // if receiver=sender then update sold
                user.setSold(user.getSold() + transaction.getAmount());
                userRepository.save(user);
                return transactionRepository.save(transaction);
            } else {
                // check if the sold is sufficient
               // if (account.getSold() >= transaction.getAmount()) {
                  //  account.setSold(account.getSold() - transaction.getAmount());
                    //accountRepository.save(account);
                   // transaction.setDate(LocalDate.now());
                    return transactionRepository.save(transaction);
                //} else {
                 //   throw new Exception("Not enough money on account");
                }

    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }


    @Override
    public Page<Transaction> pagination(User user, int pageNo, int pageSize) {
        return transactionRepository.findByEmitter(user, PageRequest.of(pageNo, pageSize));
    }

}
