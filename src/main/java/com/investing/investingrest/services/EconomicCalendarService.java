package com.investing.investingrest.services;

import com.investing.investingrest.models.CalendarEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EconomicCalendarService {

    List<CalendarEvent> getCalendarEvents();
}
