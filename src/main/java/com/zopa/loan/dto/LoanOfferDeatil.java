package com.zopa.loan.dto;

import java.math.BigDecimal;

public class LoanOfferDeatil {

    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        return "LoanOfferDeatil{" +
                "loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", monthlyRepayment=" + monthlyRepayment +
                ", totalRepayment=" + totalRepayment +
                '}';
    }
}
