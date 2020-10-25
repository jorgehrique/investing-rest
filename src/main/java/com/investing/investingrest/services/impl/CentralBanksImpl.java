package com.investing.investingrest.services.impl;

import com.investing.investingrest.exceptions.PathNotFoundException;
import com.investing.investingrest.models.CentralBank;
import com.investing.investingrest.services.CentralBanksService;
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
public class CentralBanksImpl implements CentralBanksService {

    @Autowired
    private InvestingUtil investingUtil;

    @Override
    public List<CentralBank> getCentralBanks() throws PathNotFoundException {
        Optional<Document> document = investingUtil.getDocument("central-banks");
        if(document.isEmpty()) throw new PathNotFoundException("central-banks");

//        for (Element row : document.get().select("#curr_table tr")) {
//            if (!row.html().isEmpty()) {
//                Element initial = row.select(".noWrap.left.bold span").first();
//                Element centralBank = row.select(".noWrap.left.bold a").first();
//                Element currentRate = row.select("td:nth-of-type(3)").first();
//                Element nextMeeting = row.select("td:nth-of-type(4)").first();
//                Element lastChange = row.select("td:nth-of-type(5)").first();
//
//                String initialString = (initial != null) ? initial.html() : "-";
//                String centralBankString = (centralBank != null) ? centralBank.html() : "-";
//                String currentRateString = (currentRate != null) ? currentRate.html() : "-";
//                String nextMeetingString = (nextMeeting != null) ? nextMeeting.html() : "-";
//                String lastChangeString = (lastChange != null) ? lastChange.html() : "-";
//
//                centralBanks.add(
//                        CentralBank.builder()
//                                .initials(initialString)
//                                .centralBank(centralBankString)
//                                .currentRate(currentRateString)
//                                .nextMeeting(nextMeetingString)
//                                .lastChange(lastChangeString)
//                                .build()
//                );
//            }
//        }
        List<CentralBank> centralBanks = document.get().select("#curr_table tr").stream().map(row -> {
            if (!row.html().isEmpty()) {
                Element initial = row.select(".noWrap.left.bold span").first();
                Element centralBank = row.select(".noWrap.left.bold a").first();
                Element currentRate = row.select("td:nth-of-type(3)").first();
                Element nextMeeting = row.select("td:nth-of-type(4)").first();
                Element lastChange = row.select("td:nth-of-type(5)").first();

                String initialString = (initial != null) ? initial.html() : "-";
                String centralBankString = (centralBank != null) ? centralBank.html() : "-";
                String currentRateString = (currentRate != null) ? currentRate.html() : "-";
                String nextMeetingString = (nextMeeting != null) ? nextMeeting.html() : "-";
                String lastChangeString = (lastChange != null) ? lastChange.html() : "-";

                return CentralBank.builder()
                        .initials(initialString)
                        .centralBank(centralBankString)
                        .currentRate(currentRateString)
                        .nextMeeting(nextMeetingString)
                        .lastChange(lastChangeString)
                        .build();
            } else {
                return CentralBank.builder().initials("-").build();
            }
        }).collect(Collectors.toList());
        return filterEmptyCentralBanks(centralBanks);
    }

    private List<CentralBank> filterEmptyCentralBanks(List<CentralBank> centralBankList) {
        return centralBankList
                .stream()
                .filter(centralBank -> (centralBank.getInitials() != "-"
                        && centralBank.getCentralBank() != "-"
                        && centralBank.getCurrentRate() != "-"
                        && centralBank.getNextMeeting() != "-"
                        && centralBank.getLastChange() != "-"))
                .collect(Collectors.toList());
    }
}

