package org.greenpay.services;

import org.greenpay.dto.AccountBasicInfo;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GreenPointsCreditService {

    public CompletableFuture<AccountBasicInfo> creditAmountToMainBalance(){
        return CompletableFuture.completedFuture(new AccountBasicInfo());
    }

    public AccountBasicInfo creditAmountToGreenBalance(){
        return new AccountBasicInfo();
    }
}
