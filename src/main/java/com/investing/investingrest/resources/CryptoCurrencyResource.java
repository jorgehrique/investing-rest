package com.investing.investingrest.resources;

import com.investing.investingrest.models.CryptoCurrency;
import com.investing.investingrest.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crypto/currencies")
public class CryptoCurrencyResource {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/{symbol}")
    public ResponseEntity<CryptoCurrency> getCryptoBySymbol(@PathVariable String symbol){
        return ResponseEntity
                .of(cryptoCurrencyService.getCryptoBySymbol(symbol));
    }

    @GetMapping
    public ResponseEntity<List<CryptoCurrency>> getCryptos(){
        return ResponseEntity
                .ok()
                .body(cryptoCurrencyService.getCryptos());
    }
}
