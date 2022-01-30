package org.greenpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountBasicInfo {
    private long account_no;
    private String account_holder_name;
    private double account_currency_balance;
    private double account_green_points_balance;
}
