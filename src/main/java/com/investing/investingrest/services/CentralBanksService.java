package com.investing.investingrest.services;

import com.investing.investingrest.exceptions.PathNotFoundException;
import com.investing.investingrest.models.CentralBank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CentralBanksService {

    List<CentralBank> getCentralBanks() throws PathNotFoundException;
}
