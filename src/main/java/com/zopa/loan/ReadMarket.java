package com.zopa.loan;

import com.zopa.loan.dto.Market;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.zopa.loan.MarketHeader.*;

public class ReadMarket {

    private static final Logger logger = LogManager.getLogger(ReadMarket.class);
    private String fileName;

    public ReadMarket(String fileName) {
        this.fileName = fileName;
    }

    public List<Market> getMarkets() {

        List<Market> marketList = new ArrayList<Market>();

        try {

            logger.debug("Reading Market CSV file from : " + fileName);
            Reader reader = new FileReader(fileName);
            final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : parser) {
                Market market = new Market();
                market.setLenderName(record.get(LENDER.getHeaderName()));
                market.setInterestRate(new BigDecimal(record.get(INTEREST_RATE.getHeaderName())));
                market.setAvailableAmount(new BigDecimal(record.get(AVAILABLE_AMOUNT.getHeaderName())));
                marketList.add(market);
            }

        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

        return marketList;

    }
}
