package com.smallchange.implementation;

import com.smallchange.entities.BankAccount;
import com.smallchange.repository.BankAccountRepository;
import com.smallchange.services.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    BankAccountRepository repository;

    @Override
    public BankAccount updateBankAccountDetails(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> getBankAccountByAccountNumber(String accountNumber) {
        return repository.findById(accountNumber);
    }

    @Override
    public List<BankAccount> getBankDetailsForUser(String email) {
        List<Optional<BankAccount>> details =  repository.findByEmail(email);
        List<BankAccount> result = new ArrayList<>();

        for(Optional<BankAccount> b: details) {
            result.add(b.orElse(null));
        }

        return result;
    }
}
