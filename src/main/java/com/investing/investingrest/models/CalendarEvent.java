package com.investing.investingrest.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalendarEvent {

    private String time;
    private String cur;
    private String imp;
    private String event;
}
