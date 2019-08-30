package com.investing.investingrest;

import com.investing.investingrest.services.IndicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private IndicesService indicesService;

    @Override
    public void run(String... args) throws Exception {

//        System.out.println("Runner");
//
//        System.out.println("Bovespa ->");
//        indicesService.getIndiceBySymbol("brazil-indices", "Bovespa")
//                .ifPresent(System.out::println);
//
//        System.out.println("Indices ->");
//        indicesService.getIndicesByRegion("brazil-indices").forEach(System.out::println);
    }
}
