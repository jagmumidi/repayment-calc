package com.zopa.loan;

import com.zopa.loan.dto.Market;
import com.zopa.loan.dto.ValidationMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.zopa.loan.util.CalculatorUtil.calculateMonthlyPayment;
import static com.zopa.loan.util.MarketUtil.getMinimumInterestRate;
import static com.zopa.loan.util.MarketUtil.getTotalAvailableAmount;
import static com.zopa.loan.util.ValidationUtil.validate;

public class LoanPaymentCalculator {

    public static final int loanTermInMonths = 36;

    public static void main(String... args) {

        String fileName = "src/main/resources/market.csv";
        BigDecimal loanAmountRequested = new BigDecimal(1000);
        int i = 0;

        for (String arg : args) {
            if (i == 0 && arg != null && !arg.isEmpty()) {
                fileName = arg;
            } else if (i == 1 && arg != null && !arg.isEmpty()) {
                loanAmountRequested = new BigDecimal(arg);
            }
            i++;
        }

        ReadMarket readMarket = new ReadMarket(fileName);
        List<Market> marketList = readMarket.getMarkets();

        System.out.println(marketList);
        System.out.println("Total Available Amount : " + getTotalAvailableAmount(marketList));

        ValidationMessage validationMessage = validate(loanAmountRequested, getTotalAvailableAmount(marketList));

        if (validationMessage.isPassed()) {
            BigDecimal minimumInterestRate = getMinimumInterestRate(marketList);
            BigDecimal monthlyRepayment = calculateMonthlyPayment(validationMessage.getLoanAmount().intValue(),
                    loanTermInMonths, minimumInterestRate);

            System.out.println("Loan Amount : " + validationMessage.getLoanAmount());
            System.out.println("Rate : " + minimumInterestRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING) + "%");
            System.out.println("Monthly repayment : £" + monthlyRepayment);
            System.out.println("Total repayment : £" + monthlyRepayment.multiply(new BigDecimal(loanTermInMonths)).
                    setScale(2, BigDecimal.ROUND_CEILING));

        } else {
            System.out.println("Message : " + validationMessage.getFailureMessage());
        }

    }

}
