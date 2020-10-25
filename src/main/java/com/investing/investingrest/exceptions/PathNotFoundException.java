package com.investing.investingrest.exceptions;

import org.springframework.beans.factory.annotation.Value;

public class PathNotFoundException extends Exception {

    @Value("${investing.util.base-url}")
    private static String investingUrl;

    public PathNotFoundException(String path) {
        super("Url " + investingUrl + path + " não retornou um Document válido.");
    }

}
