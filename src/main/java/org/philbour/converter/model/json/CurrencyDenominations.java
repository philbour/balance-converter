package org.philbour.converter.model.json;

import org.philbour.converter.model.Currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nullable;

public class CurrencyDenominations {

    private List<Currency> currencies = new ArrayList<>();

    @JsonCreator
    public CurrencyDenominations(@JsonProperty("currencies") List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Nullable
    public Currency getCurrency(String code) {
        return currencies.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
