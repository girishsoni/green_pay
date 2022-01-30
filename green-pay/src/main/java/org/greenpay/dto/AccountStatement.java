package org.greenpay.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class AccountStatement extends AccountBasicInfo {
    private ArrayList<Transaction> transactions;
    private Date statement_generated_at;
}
