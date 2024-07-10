package org.philbour.converter.service;

import org.apache.commons.lang3.StringUtils;
import org.philbour.converter.exception.CurrencyNotFoundException;
import org.philbour.converter.model.Currency;
import org.philbour.converter.model.json.CurrencyDenominations;
import org.philbour.converter.util.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import jakarta.annotation.PostConstruct;

@Service
public class BalanceConverterService {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceConverterService.class);

    private final JsonReader jsonReader;
    private CurrencyDenominations currencyDenominations;
    private final ObjectMapper mapper = new ObjectMapper();

    public BalanceConverterService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @PostConstruct
    public void populateCurrencyMap() {
        LOG.debug("Populating currency map");

        try {
            String json = jsonReader.readFile("denominations.json");
            currencyDenominations = mapper.readValue(json, CurrencyDenominations.class);
        } catch (IOException e) {
            LOG.error("Error reading denominations file", e);
            System.exit(-1);
        }
    }

    /**
     * Converts a balance into the least number of denominations (bills/coins)
     *
     * @param code the currency code
     * @param balance the balance to convert
     * @return the calculated denominations as a string
     * @throws CurrencyNotFoundException if the requested currency code cannot be found
     */
    public String convertBalance(String code, long balance) throws CurrencyNotFoundException {
        if (StringUtils.isBlank(code) || balance < 1) {
            return "0";
        }

        if (currencyDenominations == null) {
            return "empty";
        }

        Currency currency = currencyDenominations.getCurrency(code);

        if (currency == null) {
            LOG.warn("{} currency not found", code);
            throw new CurrencyNotFoundException(String.format("%s currency not found", code));
        }

        return currency.calculateDenominations(balance);
    }

}
