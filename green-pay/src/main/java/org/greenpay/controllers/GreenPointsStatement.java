package org.greenpay.controllers;

import io.vavr.control.Either;
import org.greenpay.dto.AccountBasicInfo;
import org.greenpay.dto.AccountStatement;
import org.greenpay.dto.CreditAmountRequest;
import org.greenpay.dto.Transaction;
import org.greenpay.services.GreenPointsCreditService;
import org.greenpay.services.GreenPointsTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController("/green-pay/statement")
public class GreenPointsStatement {

    @Autowired
    private GreenPointsTransactionService greenPointsTransactionService;

    @GetMapping("/mini/")
    public Either<AccountStatement, HttpClientErrorException> getMiniStatement(){
        return Either.left(greenPointsTransactionService.getMiniStatement());
    }

    @GetMapping("/last-transaction/")
    public Either<Transaction, HttpClientErrorException> getLasttransaction(){
        return Either.left(greenPointsTransactionService.getLastTransactionDetail());
    }
}
