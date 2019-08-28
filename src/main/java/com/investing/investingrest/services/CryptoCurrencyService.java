package com.investing.investingrest.services;

import com.investing.investingrest.models.CryptoCurrency;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CryptoCurrencyService {

    Optional<CryptoCurrency> getCryptoBySymbol(String symbol);

    List<CryptoCurrency> getCryptos();
}
