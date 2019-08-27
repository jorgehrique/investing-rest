package com.investing.investingrest;

import com.investing.investingrest.services.CentralBanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private CentralBanksService centralBanksService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Runner");
    }
}
