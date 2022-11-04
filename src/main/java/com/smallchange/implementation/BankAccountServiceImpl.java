package com.smallchange.implementation;

import com.smallchange.entities.BankAccount;
import com.smallchange.repository.BankAccountRepository;
import com.smallchange.services.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
