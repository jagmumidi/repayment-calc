package com.zopa.loan;

public enum MarketHeader {

    LENDER("Lender"),
    INTEREST_RATE("Rate"),
    AVAILABLE_AMOUNT("Available");

    private String headerName;

    MarketHeader(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return this.headerName;
    }
}
