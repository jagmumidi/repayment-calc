package com.zopa.loan.util;

import com.zopa.loan.dto.ValidationMessage;

import java.math.BigDecimal;
import java.math.MathContext;

public class ValidationUtil {

    public static final BigDecimal MINIMUM_LOAN_AMOUNT = new BigDecimal(1000);
    public static final BigDecimal MAXIMUM_LOAN_AMOUNT = new BigDecimal(15000);
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    public static ValidationMessage validate(BigDecimal amountRequested, BigDecimal totalAvailableAmount) {

        ValidationMessage validationMessage = new ValidationMessage();
        validationMessage.setLoanAmount(BigDecimal.ZERO);
        validationMessage.setPassed(false);

        if (amountRequested.compareTo(MINIMUM_LOAN_AMOUNT) < 0) {
            validationMessage.setFailureMessage("Zopa can't offer loans less then " + MINIMUM_LOAN_AMOUNT +
                    ", Requested loan amount : " + amountRequested);
        } else if (amountRequested.compareTo(MAXIMUM_LOAN_AMOUNT) > 0) {
            validationMessage.setFailureMessage("Zopa can't offer loans more then " + MAXIMUM_LOAN_AMOUNT +
                    ", Requested loan amount : " + amountRequested);
        } else if (amountRequested.compareTo(totalAvailableAmount) > 0) {
            validationMessage.setFailureMessage("It is not possible to provide a quote at that time.");
        } else {
            validationMessage.setPassed(true);
            if (isIntegerValue(amountRequested.divide(HUNDRED, MathContext.DECIMAL128))) {
                validationMessage.setLoanAmount(amountRequested);
            } else {
                validationMessage.setLoanAmount(
                        new BigDecimal(amountRequested.divide(HUNDRED, MathContext.DECIMAL128).intValue()*100));
            }
        }

        return validationMessage;
    }

    private static boolean isIntegerValue(BigDecimal bigDecimal) {
        return bigDecimal.signum() == 0 || bigDecimal.scale() <= 0 || bigDecimal.stripTrailingZeros().scale() <= 0;
    }

}
