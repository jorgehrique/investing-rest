package com.investing.investingrest.services;

import com.investing.investingrest.models.Indice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IndicesService {

    Optional<Indice> getIndiceBySymbol(String region, String symbol);

    List<Indice> getIndicesByRegion(String region);
}
