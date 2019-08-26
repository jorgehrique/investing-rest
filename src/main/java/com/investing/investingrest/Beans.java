package com.investing.investingrest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Beans {

    @Bean
    public WebClient getWebClient(){
        return WebClient.create();
    }
}
