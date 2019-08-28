package com.investing.investingrest.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoCurrency {

    private String name;
    private String symbol;
    private String priceUSD;
    private String marketCap;
    private String vol24H;
    private String totalVol;
    private String chg24H;
    private String chg7D;

}
