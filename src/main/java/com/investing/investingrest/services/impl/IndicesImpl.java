package com.investing.investingrest.services.impl;

import com.investing.investingrest.models.Indice;
import com.investing.investingrest.services.IndicesService;
import com.investing.investingrest.utils.InvestingUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IndicesImpl implements IndicesService {

    private Document document;

    @Override
    public Optional<Indice> getIndiceBySymbol(String region, String symbol) {
        return this.getIndicesByRegion(region)
                .stream()
                .filter(indice -> indice.getSymbol().equals(symbol))
                .findFirst();
    }

    @Override
    public List<Indice> getIndicesByRegion(String region) {
        List<Indice> indices = new ArrayList<>();
        this.document = InvestingUtil.getDocument("indices/" + region);

        for (Element row : document.select("#cr1 tr")) {
            if (row != null) {
                Element symbol = row.select(".bold.left.noWrap a").first();
                List<Element> elements = row.select("td");

                String symbolString = (symbol != null) ? symbol.html() : "-";

                if (elements != null && elements.size() >= 7) {
                    String lastString = (elements.get(2) != null) ? elements.get(2).html() : "-";
                    String highString = (elements.get(3) != null) ? elements.get(3).html() : "-";
                    String lowString = (elements.get(4) != null) ? elements.get(4).html() : "-";
                    String chgString = (elements.get(5) != null) ? elements.get(5).html() : "-";
                    String chgPercentString = (elements.get(6) != null) ? elements.get(6).html() : "-";
                    String timeString = (elements.get(7) != null) ? elements.get(7).html() : "-";

                    indices.add(
                            Indice.builder()
                                    .symbol(symbolString)
                                    .last(lastString)
                                    .high(highString)
                                    .low(lowString)
                                    .chg(chgString)
                                    .chgPercent(chgPercentString)
                                    .time(timeString)
                                    .build()
                    );
                }
            }
        }
        return indices;
    }
}
