package org.greenpay.services;

import org.greenpay.dto.AccountBasicInfo;
import org.greenpay.dto.AccountStatement;
import org.greenpay.dto.Transaction;
import org.springframework.stereotype.Service;

@Service
public class GreenPointsTransactionService {

    public AccountStatement getMiniStatement(){
        return new AccountStatement();
    }

    public Transaction getLastTransactionDetail(){ return new Transaction(); }
}
