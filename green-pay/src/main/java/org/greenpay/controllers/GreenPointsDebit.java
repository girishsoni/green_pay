package org.greenpay.controllers;

import io.vavr.control.Either;
import org.greenpay.dto.AccountBasicInfo;
import org.greenpay.dto.DebitAmountRequest;
import org.greenpay.services.GreenPointsDebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController("/green-pay/debit")
public class GreenPointsDebit {

    @Autowired
    private GreenPointsDebitService greenPointsDebitService;

    @PostMapping("/")
    public Either<AccountBasicInfo, HttpClientErrorException> getAccountBalance(
            @RequestBody DebitAmountRequest debitAmountRequest){
        return debitAmountRequest.getDebit_type().equalsIgnoreCase("currency")?
                Either.left(greenPointsDebitService.debitAmountFromMainBalance()):
                debitAmountRequest.getDebit_type().equalsIgnoreCase("green-points")?
                        Either.left(greenPointsDebitService.debitAmountFromGreenBalance()):
                        Either.right(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }

}
