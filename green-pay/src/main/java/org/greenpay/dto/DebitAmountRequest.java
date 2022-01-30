package org.greenpay.dto;

import lombok.Data;

@Data
public class DebitAmountRequest extends AccountBasicInfo {
    private String debit_type;
    private double debit_amount;
}
