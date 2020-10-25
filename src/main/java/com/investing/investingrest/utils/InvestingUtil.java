package com.investing.investingrest.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvestingUtil {

    @Value("${investing.util.base-url}")
    private String investingUrl;

    @Value("${investing.util.browser}")
    private String browser;

    public Optional<Document> getDocument(String path) {
        try {
            return Optional.of(
                Jsoup
                .connect(investingUrl + path)
                .userAgent(browser)
                .get()
            );

        } catch(Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    // java.net.SocketTimeoutException: Read timed out

}
