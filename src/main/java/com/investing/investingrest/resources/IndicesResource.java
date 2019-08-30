package com.investing.investingrest.resources;

import com.investing.investingrest.models.Indice;
import com.investing.investingrest.services.IndicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("indices")
public class IndicesResource {

    @Autowired
    private IndicesService indicesService;

    @GetMapping("/{region}/{symbol}")
    public ResponseEntity<Indice> getIndiceBySymbol(@PathVariable("region") String region,
                                                    @PathVariable("symbol") String symbol){
        return ResponseEntity.of(indicesService.getIndiceBySymbol(region, symbol));
    }

    @GetMapping("/{region}")
    public ResponseEntity<List<Indice>> getIndicesList(@PathVariable String region){
        return ResponseEntity.ok(indicesService.getIndicesByRegion(region));
    }
}
