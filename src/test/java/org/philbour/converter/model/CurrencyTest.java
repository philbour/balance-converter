package org.philbour.converter.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CurrencyTest {

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(Currency.class).verify();
    }
}
