package org.greenpay.controllers;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.greenpay.dto.CreditAmountRequest;
import org.greenpay.services.GreenPointsCreditService;
import org.greenpay.utility.ApiTimeoutHandler;
import org.greenpay.utility.HttpResponseAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
//@Produces({MediaType.APPLICATION_JSON})
//@Consumes({MediaType.APPLICATION_JSON})
//@Path("/.")
public class GreenPointsCredit {

    @Autowired
    private GreenPointsCreditService greenPointsCreditService;

    /*@PostMapping("/green-pay/credit/")
    public Either<AccountBasicInfo, HttpClientErrorException> performAccountCredit(
            @RequestBody CreditAmountRequest creditAmountRequest){
        return creditAmountRequest.getCredit_type().equalsIgnoreCase("currency")?
                Either.left(greenPointsCreditService.creditAmountToMainBalance()):
                creditAmountRequest.getCredit_type().equalsIgnoreCase("green-points")?
                        Either.left(greenPointsCreditService.creditAmountToGreenBalance()):
                        Either.right(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }*/

    /*@PostMapping("/green-pay/credit/")
    public Try<AccountBasicInfo> performAccountCredit(
            @RequestBody CreditAmountRequest creditAmountRequest){
        return creditAmountRequest.getCredit_type().equalsIgnoreCase("currency")?
                Try.success(greenPointsCreditService.creditAmountToMainBalance()):
                creditAmountRequest.getCredit_type().equalsIgnoreCase("green-points")?
                        Try.success(greenPointsCreditService.creditAmountToGreenBalance()):
                        Try.failure(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }*/

    @PostMapping
    @POST
    @Path("/green-pay/credit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void performAccountCredit(
            @Suspended final AsyncResponse asynchResponse,
            @RequestBody CreditAmountRequest creditAmountRequest) throws HttpClientErrorException{

        //ApiTimeoutHandler.setTimeout(asynchResponse, 1, TimeUnit.MICROSECONDS, "Post /green-pay/credit/");

        if (creditAmountRequest.getCredit_type().equalsIgnoreCase("currency")){
            greenPointsCreditService.creditAmountToMainBalance()
                    .thenApply(accountBasicInfo -> Response.ok(accountBasicInfo).build())
                    .handle(new HttpResponseAcceptor(asynchResponse));
        } else if(creditAmountRequest.getCredit_type().equalsIgnoreCase("green-points")) {
            greenPointsCreditService.creditAmountToGreenBalance();
        } else {
            new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }
}
