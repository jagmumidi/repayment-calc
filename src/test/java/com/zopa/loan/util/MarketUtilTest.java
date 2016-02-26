package com.zopa.loan.util;

import com.zopa.loan.dto.Market;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static com.zopa.loan.util.MarketUtil.getTotalAvailableAmount;
import static com.zopa.loan.util.MarketUtil.getMinimumInterestRate;

public class MarketUtilTest {

    @Test
    public void noMarketTotalValue() {
        assertEquals(BigDecimal.ZERO, getTotalAvailableAmount(null));
    }

    @Test
    public void emptyMarketTotalValue() {
        List<Market> markets = new ArrayList<Market>();
        assertEquals(BigDecimal.ZERO, MarketUtil.getTotalAvailableAmount(markets));
    }

    @Test
    public void singleMarketTotalValue() {
        BigDecimal jagAvailableAmount = new BigDecimal(7000);
        List<Market> markets = new ArrayList<Market>();
        Market market1 = new Market();
        market1.setLenderName("Jag");
        market1.setAvailableAmount(jagAvailableAmount);
        market1.setInterestRate(new BigDecimal("069"));
        markets.add(market1);
        assertEquals(jagAvailableAmount, MarketUtil.getTotalAvailableAmount(markets));
    }


    @Test
    public void twoMarketTotalValue() {
        BigDecimal jagAvailableAmount = new BigDecimal(7000);
        BigDecimal bertieAvailableAmount = new BigDecimal(9000);
        BigDecimal totalAvailableAmount = jagAvailableAmount.add(bertieAvailableAmount);
        List<Market> markets = new ArrayList<Market>();
        Market market1 = new Market();
        market1.setLenderName("Jag");
        market1.setAvailableAmount(jagAvailableAmount);
        market1.setInterestRate(new BigDecimal("069"));
        markets.add(market1);
        Market market2 = new Market();
        market2.setLenderName("Bertie");
        market2.setAvailableAmount(bertieAvailableAmount);
        market2.setInterestRate(new BigDecimal("059"));
        markets.add(market2);
        assertEquals(totalAvailableAmount, MarketUtil.getTotalAvailableAmount(markets));
    }

    @Test(expected = NoSuchElementException.class)
    public void noMarketMinimumInterestRate() {
        getMinimumInterestRate(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyMarketMinimumInterestRate() {
        List<Market> markets = new ArrayList<Market>();
        getMinimumInterestRate(markets);
    }

    @Test
    public void singleMarketMinimumInterestRate() {
        BigDecimal jagAvailableAmount = new BigDecimal(7000);
        BigDecimal jagInterstRate = new BigDecimal(.069);
        List<Market> markets = new ArrayList<Market>();
        Market market1 = new Market();
        market1.setLenderName("Jag");
        market1.setAvailableAmount(jagAvailableAmount);
        market1.setInterestRate(jagInterstRate);
        markets.add(market1);
        assertEquals(jagInterstRate, getMinimumInterestRate(markets));
    }


    @Test
    public void twoMarketMinimumInterestRate() {
        BigDecimal jagAvailableAmount = new BigDecimal(7000);
        BigDecimal jagInterestRate = new BigDecimal(.069);
        BigDecimal bertieAvailableAmount = new BigDecimal(9000);
        BigDecimal bertieInterestRate = new BigDecimal(.059);
        List<Market> markets = new ArrayList<Market>();
        Market market1 = new Market();
        market1.setLenderName("Jag");
        market1.setAvailableAmount(jagAvailableAmount);
        market1.setInterestRate(jagInterestRate);
        markets.add(market1);
        Market market2 = new Market();
        market2.setLenderName("Bertie");
        market2.setAvailableAmount(bertieAvailableAmount);
        market2.setInterestRate(bertieInterestRate);
        markets.add(market2);
        assertEquals(bertieInterestRate.setScale(3, BigDecimal.ROUND_CEILING),
                getMinimumInterestRate(markets).setScale(3, BigDecimal.ROUND_CEILING));
    }

}
