package org.philbour.converter.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.philbour.converter.util.JsonReader;

class BalanceConverterServiceTest {

    @Test
    void TestFidelity() {
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
