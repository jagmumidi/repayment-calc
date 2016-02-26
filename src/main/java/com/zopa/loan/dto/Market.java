package com.zopa.loan.dto;

import java.math.BigDecimal;

public class Market {

    private String lenderName;
    private BigDecimal interestRate;
    private BigDecimal availableAmount;

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public String toString() {
        return "Market{" +
                "lenderName='" + lenderName + '\'' +
                ", interestRate=" + interestRate +
                ", availableAmount=" + availableAmount +
                '}';
    }
}
