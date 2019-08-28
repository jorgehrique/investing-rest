package com.investing.investingrest.services.impl;

import com.investing.investingrest.models.CryptoCurrency;
import com.investing.investingrest.services.CryptoCurrencyService;
import com.investing.investingrest.utils.InvestingUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptoCurrencyImpl implements CryptoCurrencyService {

    private final Document document;

    public CryptoCurrencyImpl() {
        this.document = InvestingUtil.getDocument("crypto/currencies");
    }

    @Override
    public Optional<CryptoCurrency> getCryptoBySymbol(final String symbol) {
        return this.getCryptos()
                .stream()
                .filter(c -> c.getSymbol().equals(symbol.toUpperCase()))
                .findFirst();
    }

    @Override
    public List<CryptoCurrency> getCryptos() {
        List<CryptoCurrency> cryptos = new ArrayList<>();

        for (Element row : document.select("#fullColumn table tr")) {
            if (row != null) {
                Element name = row.select(".cryptoName a").first();
                Element symbol = row.select(" .symb").first();
                Element priceUSD = row.select(".price a").first();
                Element marketCap = row.select(".js-market-cap").first();
                Element vol = row.select(".js-24h-volume").first();
                Element totalVol = row.select(".js-total-vol").first();
                Element chg24 = row.select(".js-currency-change-24h").first();
                Element chg7 = row.select(".js-currency-change-7d").first();

                String nameString = (name != null) ? name.html() : "-";
                String symbolString = (symbol != null) ? symbol.html() : "-";
                String priceUSDString = (priceUSD != null) ? priceUSD.html() : "-";
                String marketCapString = (marketCap != null) ? marketCap.html() : "-";
                String volString = (vol != null) ? vol.html() : "-";
                String totalVolString = (totalVol != null) ? totalVol.html() : "-";
                String chg24String = (chg24 != null) ? chg24.html() : "-";
                String chg7String = (chg7 != null) ? chg7.html() : "-";

                cryptos.add(
                        CryptoCurrency.builder()
                                .name(nameString)
                                .symbol(symbolString)
                                .priceUSD(priceUSDString)
                                .marketCap(marketCapString)
                                .vol24H(volString)
                                .totalVol(totalVolString)
                                .chg24H(chg24String)
                                .chg7D(chg7String)
                                .build()
                );
            }
        }

        return filterEmptyCryptos(cryptos);
    }

    private List<CryptoCurrency> filterEmptyCryptos(List<CryptoCurrency> cryptos) {
        return cryptos
                .stream()
                .filter(crypto -> (crypto.getName() != "-" && crypto.getPriceUSD() != "-"))
                .collect(Collectors.toList());
    }

}
