package org.greenpay.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction extends AccountBasicInfo {
    private String transaction_type;
    private double transaction_amount;
    private double transaction_amount_type;
    private double updated_currency_balance;
    private double updated_green_points_balance;
    private Date transactionDatetime;
}
