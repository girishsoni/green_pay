package org.greenpay.dto;

import lombok.Data;

@Data
public class CreditAmountRequest extends AccountBasicInfo {
    private String credit_type;
    private double credit_amount;
}
