package com.credsystem.desafio.utils;

import com.credsystem.desafio.model.Client;
import com.credsystem.desafio.model.CreditCard;

import java.math.BigDecimal;

public class CreditCardUtils {

    public static BigDecimal setClientCreditLimit(Client client){
        BigDecimal actualLimit;
        BigDecimal presumedLimit = client.getSalary().multiply(new BigDecimal("0.3")).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        if (presumedLimit.doubleValue() > 2000.0){
            actualLimit = new BigDecimal("2000");
        } else {
            actualLimit = presumedLimit;
        }

        return actualLimit;
    }

    public static BigDecimal newLimitAfterTransaction(CreditCard card, BigDecimal amount){
        return card.getAvailableLimit().subtract(amount);
    }
}
