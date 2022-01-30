package org.greenpay.services;

import org.greenpay.dto.AccountBasicInfo;
import org.springframework.stereotype.Service;

@Service
public class GreenPointsDebitService {

    public AccountBasicInfo debitAmountFromMainBalance(){
        return new AccountBasicInfo();
    }

    public AccountBasicInfo debitAmountFromGreenBalance(){
        return new AccountBasicInfo();
    }
}
