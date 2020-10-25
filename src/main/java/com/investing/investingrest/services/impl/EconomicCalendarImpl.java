package com.investing.investingrest.services.impl;

import com.investing.investingrest.exceptions.PathNotFoundException;
import com.investing.investingrest.models.CalendarEvent;
import com.investing.investingrest.services.EconomicCalendarService;
import com.investing.investingrest.utils.InvestingUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EconomicCalendarImpl implements EconomicCalendarService {

    @Autowired
    private InvestingUtil investingUtil;

    @Override
    public List<CalendarEvent> getCalendarEvents() throws PathNotFoundException {
        Optional<Document> document = investingUtil.getDocument("economic-calendar");
        if(document.isEmpty()) throw new PathNotFoundException("economic-calendar");

        List<CalendarEvent> events = new ArrayList<>();
        for (Element row : document.get().select("#economicCalendarData tr")) {
            if (!row.html().isEmpty()) {
                Element time = row.select(".js-time.time.left.first").first();
                Element cur = row.select(".noWrap.flagCur.left").first();
                Element imp = row.select(".noWrap.sentiment.textNum.left").first();
                Element event = row.select(".event.left a").first();

                String timeString = (time != null) ? time.html() : "-";
                String curString = (cur != null) ? cur.html() : "-";
                String impString = (imp != null) ? imp.html() : "-";
                String eventString = (event != null) ? event.html() : "-";

                String[] curSplit = curString.split("</span>");
                curString = (curSplit.length > 1) ? curSplit[1] : curString;

                impString = String.valueOf(bullishCounter(impString));

                events.add(
                        CalendarEvent
                                .builder()
                                .time(timeString)
                                .cur(curString)
                                .imp(impString)
                                .event(eventString)
                                .build()
                );
            }
        }

        return filterEmptyEvents(events);
    }

    private int bullishCounter(String imp) {
        String key = "grayFullBullishIcon";
        int it = imp.length() - key.length();
        int count = 0;
        for (int i = 0; i < it; i++) {
            if (imp.substring(i, i + key.length()).equals(key)) {
                count++;
            }
        }
        return count;
    }

    private List<CalendarEvent> filterEmptyEvents(List<CalendarEvent> events) {
        return events
                .stream()
                .filter(event -> event.getTime() != "-"
                        && event.getCur() != "-"
                        && event.getImp() != "0"
                        && event.getEvent() != "-"
                )
                .collect(Collectors.toList());
    }
}
