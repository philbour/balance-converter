package org.philbour.converter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.philbour.converter.exception.CurrencyNotFoundException;
import org.philbour.converter.util.JsonReader;

class BalanceConverterServiceTest {

    @Test
    void calculate_USDCurrency_ReturnsBalance() throws CurrencyNotFoundException {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        String result = denominationCalculator.calculate("USD", 87); // $0.87

        assertEquals("3 Quarter, 1 Dime, 2 Penny coins", result);
    }

    @Test
    void calculate_EurCurrency_ReturnsBalance() throws CurrencyNotFoundException {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        String result = denominationCalculator.calculate("EUR", 287); // €2.87

        assertEquals("1 2 Euro, 1 50c, 1 20c, 1 10c, 1 5c, 1 2c coins", result);
    }

    @Test
    void calculate_CurrencyNotFound_ThrowsException() {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        Exception exception = assertThrows(CurrencyNotFoundException.class, () -> {
            denominationCalculator.calculate("GBP", 287); // £2.87
        });

        assertThat(exception.getMessage()).contains("currency not found");
    }

    @Test
    void calculate_CurrencyNotSupplied_ReturnsZero() throws CurrencyNotFoundException {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        String result = denominationCalculator.calculate("", 87); // $0.87

        assertEquals("0", result);
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    void calculate_BalanceNotValid_ReturnsZero(long balance) throws CurrencyNotFoundException {
        BalanceConverterService denominationCalculator = new BalanceConverterService(new JsonReader());
        denominationCalculator.populateCurrencyMap();

        String result = denominationCalculator.calculate("EUR", balance); // $0.87

        assertEquals("0", result);
    }

}
