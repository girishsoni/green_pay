package org.greenpay.controllers;

import io.vavr.control.Either;
import org.greenpay.dto.AccountBasicInfo;
import org.greenpay.dto.CreditAmountRequest;
import org.greenpay.services.GreenPointsCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController("/green-pay/credit")
public class GreenPointsCredit {

    @Autowired
    private GreenPointsCreditService greenPointsCreditService;

    @PostMapping("/")
    public Either<AccountBasicInfo, HttpClientErrorException> getAccountBalance(
            @RequestBody CreditAmountRequest creditAmountRequest){
        return creditAmountRequest.getCredit_type().equalsIgnoreCase("currency")?
                Either.left(greenPointsCreditService.creditAmountToMainBalance()):
                creditAmountRequest.getCredit_type().equalsIgnoreCase("green-points")?
                        Either.left(greenPointsCreditService.creditAmountToGreenBalance()):
                        Either.right(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }

}
