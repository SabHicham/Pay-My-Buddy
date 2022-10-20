package com.paymybuddy.service.impl;


import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public UserRepository userRepository;

    public SecurityContext context = SecurityContextHolder.getContext();




    @Override
    public Transaction createTransaction(Transaction transaction) throws Exception {
        User user = userRepository.findByEmail(transaction.getEmitterEmail());
        User receiver = userRepository.findByEmail(transaction.getReceiverEmail());
        transaction.setEmitter(user);
        transaction.setReceiver(receiver);
        if (receiver.getEmail() != user.getEmail()) {
            // if receiver=sender then update sold
            if (user.getSold() >= transaction.getAmount()) {
                user.setSold(user.getSold() - transaction.getAmount());
                userRepository.save(user);
                receiver.setSold(receiver.getSold() + transaction.getAmount()*0.995);
                userRepository.save(receiver);
                return transactionRepository.save(transaction);

            }

        }
    return null;

    }

    @Override
    public List<Transaction> findByEmitter(User user) {
        return transactionRepository.findByEmitter(user);
    }




    @Override
    public void transfetMoneyFromBank(int amount) {
        User userConnected = userRepository.findByEmail(context.getAuthentication().getName());
        if (amount > 0){
            userConnected.setSold(userConnected.getSold()+amount);
            userRepository.save(userConnected);
        }
    }

}
