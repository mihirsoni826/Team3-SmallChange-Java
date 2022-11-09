package com.smallchange.services;

import com.smallchange.entities.BankAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBankAccountService {
    BankAccount updateBankAccountDetails(BankAccount bankAccount);
    Optional<BankAccount> getBankAccountByAccountNumber(String accountNumber);
    List<BankAccount> getBankDetailsForUser(String email);
}
