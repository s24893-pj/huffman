package com.example.NBP.Service;

import com.example.NBP.Model.NbpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NBPService {
    private static final String NBP_API_BASE_URL = "http://api.nbp.pl/api/exchangerates/rates";
    private final RestTemplate restTemplate;

    public NBPService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NbpResponse getCurrencyRates(String table, String code, int lastDays) {
        String url = String.format("%s/%s/%s/last/%d", NBP_API_BASE_URL, table, code, lastDays);
        return restTemplate.getForObject(url, NbpResponse.class);
    }
}
