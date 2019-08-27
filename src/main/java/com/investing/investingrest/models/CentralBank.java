package com.investing.investingrest.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CentralBank {

    private String initials;
    private String centralBank;
    private String currentRate;
    private String nextMeeting;
    private String lastChange;
}
