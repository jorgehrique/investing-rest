package com.investing.investingrest.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InvestingUtil {

    private static final String investingUrl = "https://www.investing.com/";
    private static final String browser = "Mozilla/5.0";

    public static Document getDocument(String path) {
        try {
            return Jsoup
                .connect(investingUrl + path)
                .userAgent(browser)
                .get();
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
