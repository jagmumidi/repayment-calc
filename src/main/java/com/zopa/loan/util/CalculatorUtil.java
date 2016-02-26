package com.zopa.loan.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorUtil {

    public static BigDecimal calculateMonthlyPayment(int loanAmount, int termInMonths, BigDecimal interestRate) {

        BigDecimal monthlyRate = interestRate.divide(new BigDecimal(12), MathContext.DECIMAL128);
        BigDecimal monthlyPayment = monthlyRate.multiply(new BigDecimal(loanAmount)).
                divide(new BigDecimal(1-Math.pow(monthlyRate.add(BigDecimal.ONE).doubleValue(), -termInMonths)),
                        MathContext.DECIMAL128).
                setScale(2, RoundingMode.CEILING);

        return monthlyPayment;
    }

}
