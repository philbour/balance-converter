/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya Inc. The copyright
 * notice above does not evidence any actual or intended publication of such source code. Some third-party source code
 * components may have been modified from their original versions by Avaya Inc. The modifications are Copyright Avaya
 * Inc., All Rights Reserved. Avaya - Confidential & Restricted. May not be distributed further without written
 * permission of the Avaya owner.
 */

package org.philbour.converter.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDenominations {

    private final List<Currency> currencies = new ArrayList<>();

    public Currency getCurrency(String code) {
        return currencies.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst().get();
    }

}
