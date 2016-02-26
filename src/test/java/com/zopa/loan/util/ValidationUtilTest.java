package com.zopa.loan.util;

import com.zopa.loan.dto.ValidationMessage;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static com.zopa.loan.util.ValidationUtil.MINIMUM_LOAN_AMOUNT;
import static com.zopa.loan.util.ValidationUtil.MAXIMUM_LOAN_AMOUNT;

public class ValidationUtilTest {

    public BigDecimal availableAmount = new BigDecimal(9000);

    @Test
    public void lessThenMinimumAmount() {
        BigDecimal loanAmount = new BigDecimal(900);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertFalse(message.isPassed());
        String expectedFailureMessage = "Zopa can't offer loans less then " + MINIMUM_LOAN_AMOUNT +
                ", Requested loan amount : " + loanAmount;
        assertEquals(expectedFailureMessage, message.getFailureMessage());
    }

    @Test
    public void negativeThenMinimumAmount() {
        BigDecimal loanAmount = new BigDecimal(-100);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertFalse(message.isPassed());
        String expectedFailureMessage = "Zopa can't offer loans less then " + MINIMUM_LOAN_AMOUNT +
                ", Requested loan amount : " + loanAmount;
        assertEquals(expectedFailureMessage, message.getFailureMessage());
    }

    @Test
    public void loanAmountThousand() {
        BigDecimal loanAmount = new BigDecimal(1000);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertTrue(message.isPassed());
        assertEquals(loanAmount, message.getLoanAmount());
    }

    @Test
    public void loanAmountSevenThousand() {
        BigDecimal loanAmount = new BigDecimal(7000);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertTrue(message.isPassed());
        assertEquals(loanAmount, message.getLoanAmount());
    }

    @Test
    public void loanAmountNineThousand() {
        BigDecimal loanAmount = new BigDecimal(9000);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertTrue(message.isPassed());
        assertEquals(loanAmount, message.getLoanAmount());
    }

    @Test
    public void loanAmountAboveAvailableAmount() {
        BigDecimal loanAmount = new BigDecimal(10000);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertFalse(message.isPassed());
        assertEquals("It is not possible to provide a quote at that time.", message.getFailureMessage());
        assertEquals(BigDecimal.ZERO, message.getLoanAmount());
    }

    @Test
    public void loanAmountNonHundred() {
        BigDecimal loanAmount = new BigDecimal(7920);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertTrue(message.isPassed());
        assertEquals(new BigDecimal(7900), message.getLoanAmount());
    }

    @Test
    public void loanAmountAboveMaximumOfferAmount() {
        BigDecimal loanAmount = new BigDecimal(18000);
        ValidationMessage message = ValidationUtil.validate(loanAmount, availableAmount);
        assertFalse(message.isPassed());
        String expectedFailureMessage = "Zopa can't offer loans more then " + MAXIMUM_LOAN_AMOUNT +
                ", Requested loan amount : " + loanAmount;
        assertEquals(expectedFailureMessage, message.getFailureMessage());
    }

}
