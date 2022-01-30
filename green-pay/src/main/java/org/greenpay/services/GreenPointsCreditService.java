package org.greenpay.services;

import org.greenpay.dto.AccountBasicInfo;
import org.springframework.stereotype.Service;

@Service
public class GreenPointsCreditService {

    public AccountBasicInfo creditAmountToMainBalance(){
        return new AccountBasicInfo();
    }

    public AccountBasicInfo creditAmountToGreenBalance(){
        return new AccountBasicInfo();
    }
}
