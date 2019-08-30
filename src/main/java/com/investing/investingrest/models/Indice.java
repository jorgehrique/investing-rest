package com.investing.investingrest.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Indice {

    private String symbol;
    private String last;
    private String high;
    private String low;
    private String chg;
    private String chgPercent;
    private String time;

}
