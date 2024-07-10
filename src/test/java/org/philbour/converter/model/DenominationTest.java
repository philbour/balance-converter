package org.philbour.converter.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class DenominationTest {

    @Test
    void testCompareTo() {
        Denomination d1 = new Denomination("USD", "1Cent", 1, DenominationType.COIN);
        Denomination d2 = new Denomination("USD", "5Cent", 5, DenominationType.COIN);
        Denomination d3 = new Denomination("USD", "20Dollar", 20, DenominationType.NOTE);

        assertEquals(0, d1.compareTo(d1));
        assertEquals(1, d1.compareTo(d2));
        assertEquals(-1, d3.compareTo(d2));
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.simple().forClass(Denomination.class).verify();
    }
}
