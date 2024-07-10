/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya Inc. The copyright
 * notice above does not evidence any actual or intended publication of such source code. Some third-party source code
 * components may have been modified from their original versions by Avaya Inc. The modifications are Copyright Avaya
 * Inc., All Rights Reserved. Avaya - Confidential & Restricted. May not be distributed further without written
 * permission of the Avaya owner.
 */

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
