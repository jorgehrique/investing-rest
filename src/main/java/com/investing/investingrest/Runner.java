package com.investing.investingrest;

import com.investing.investingrest.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @Override
    public void run(String... args) throws Exception {

        //System.out.println("Runner");
    }
}
