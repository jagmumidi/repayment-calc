package com.zopa.loan;

import com.zopa.loan.dto.Market;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shan on 25/02/2016.
 */
public class ReadMarketTest {

    ReadMarket readMarket;

    @Before
    public void setup() {
        readMarket = new ReadMarket("src/main/resources/market.csv");
    }

    @Test
    public void readMarketSize() {
        assertEquals(7, readMarket.getMarkets().size());
    }

    @Test
    public void verifyBobMarketValue() {
        Market bobMarket = readMarket.getMarkets().get(0);
        assertEquals("Bob", bobMarket.getLenderName());
        assertEquals(new BigDecimal(0.075).setScale(3, RoundingMode.CEILING),
                bobMarket.getInterestRate().setScale(3, RoundingMode.CEILING));
        assertEquals(new BigDecimal(640.00).setScale(2, RoundingMode.CEILING),
                bobMarket.getAvailableAmount().setScale(2, RoundingMode.CEILING));
    }

}
