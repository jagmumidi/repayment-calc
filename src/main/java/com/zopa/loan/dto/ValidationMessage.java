package com.zopa.loan.dto;

import java.math.BigDecimal;

public class ValidationMessage {

    private boolean passed;
    private String failureMessage;
    private BigDecimal loanAmount;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public String toString() {
        return "ValidationMessage{" +
                "passed=" + passed +
                ", failureMessage='" + failureMessage + '\'' +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
