package com.zopa.loan.util;

import com.zopa.loan.dto.Market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shan on 26/02/2016.
 */
public class MarketUtil {

    public static BigDecimal getTotalAvailableAmount(List<Market> marketList) {

        BigDecimal totalAvailableAmount = BigDecimal.ZERO;

        if (marketList != null && marketList.size() > 0) {
            for (Market market : marketList) {
                totalAvailableAmount = totalAvailableAmount.add(market.getAvailableAmount());
            }
        }

        return totalAvailableAmount;
    }

    public static BigDecimal getMinimumInterestRate(List<Market> marketList) {

        List<BigDecimal> interestRateList = new ArrayList<BigDecimal>();

        if (marketList != null && marketList.size() > 0) {
            for (Market market : marketList) {
                interestRateList.add(market.getInterestRate());
            }
        }

        return Collections.min(interestRateList);

    }

}
