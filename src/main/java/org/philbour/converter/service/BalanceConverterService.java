package org.philbour.converter.service;

import org.philbour.converter.model.Currency;
import org.philbour.converter.model.json.CurrencyDenominations;
import org.philbour.converter.util.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class BalanceConverterService {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceConverterService.class);

    private final JsonReader jsonReader;
    private CurrencyDenominations cd;

    public BalanceConverterService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @PostConstruct
    public void populateCurrencyMap() throws JsonMappingException, JsonProcessingException {
        LOG.debug("Populating currency map");

        String json = jsonReader.readFile("denominations.json");

        ObjectMapper mapper = new ObjectMapper();
        cd = mapper.readValue(json, CurrencyDenominations.class);
    }

    public String calculate(String code, long balance) {
        // add validation and throw exceptions?
        // null checks?

        Currency currency = cd.getCurrency(code);

        String result = currency.calculate(balance);

        System.out.println(result);
        return result;
    }

}
