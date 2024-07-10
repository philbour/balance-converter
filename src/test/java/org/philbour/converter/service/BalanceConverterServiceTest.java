package org.philbour.converter.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.philbour.converter.util.JsonReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

class BalanceConverterServiceTest {

    @Test
    void TestConverter() throws JsonMappingException, JsonProcessingException {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        denominationCalculator.calculate("USD", 87);
        denominationCalculator.calculate("USD", 287); // $2.87
        denominationCalculator.calculate("USD", 36);
        denominationCalculator.calculate("USD", 5);
        denominationCalculator.calculate("USD", 4);
        denominationCalculator.calculate("USD", 4389); // $43.89
        denominationCalculator.calculate("USD", 38157); // $381.57
        // denominationCalculator.calculate("USD", -1);

        denominationCalculator.calculate("EUR", 287); // €2.87

        assertTrue(true);
    }
}
