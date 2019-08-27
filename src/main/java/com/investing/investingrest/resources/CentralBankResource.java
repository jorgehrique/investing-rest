package com.investing.investingrest.resources;

import com.investing.investingrest.models.CentralBank;
import com.investing.investingrest.services.CentralBanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("central-banks")
public class CentralBankResource {

    @Autowired
    private CentralBanksService centralBanksService;

    @GetMapping
    public ResponseEntity<List<CentralBank>> getCentralBanksList(){
        return ResponseEntity
                .ok()
                .body(centralBanksService.getCentralBanks());
    }

}
