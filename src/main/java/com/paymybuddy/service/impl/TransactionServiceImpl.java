package com.paymybuddy.service.impl;


import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.BankRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;





@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;


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
    public Page<Transaction> pagination(User user, int pageNo, int pageSize) {
        return transactionRepository.findByEmitter(user, PageRequest.of(pageNo, pageSize));
    }

   @Override
    public Transaction transferMoneyToBank(Transaction transaction, Authentication authentication) {
        checkAmountIsValid(transaction.getAmount());
        Bank bank = checkBankisValid(transaction.getEmitterEmail());
        User userDetails = (User) authentication.getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getEmail());
        if (currentUser.getSold() >= transaction.getAmount()) {

            currentUser.setSold(round(currentUser.getSold() - transaction.getAmount(), 2));
           userRepository.save(currentUser);
            addTransferUserToBankOperation(currentUser, bank, transaction);
            Transaction transaction1 = new Transaction();
            transaction1.setEmitter(currentUser);
            return transaction1;
        }
       return null;
    }

    @Override
    public void transfetMoneyFromBank(int amount) {
        User userConnected = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (amount > 0){
            userConnected.setSold(userConnected.getSold()+amount);
            userRepository.save(userConnected);
        }
    }

    private void addTransferUserToBankOperation(User currentUser, Bank bank, Transaction transaction) {
    }

    public void checkAmountIsValid(double amount) {
        if (!(BigDecimal.valueOf(amount).scale() <= 2) || amount <= 0) {

        }

    }
    private Bank checkBankisValid(String bankName) {
        Bank bank = bankRepository.findByName(bankName);
        if (bank == null) {

        }
        return bank;
    }
    public static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
