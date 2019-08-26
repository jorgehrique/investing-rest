package com.investing.investingrest.resources;

import com.investing.investingrest.models.CalendarEvent;
import com.investing.investingrest.services.EconomicCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/economic-calendar")
public class EconomicCalendarResource {

    @Autowired
    private EconomicCalendarService economicCalendarService;

    @GetMapping
    public ResponseEntity<List<CalendarEvent>> getListCalendarEvent(){
        return ResponseEntity
                .ok()
                .body(economicCalendarService.getCalendarEvents());
    }
}
