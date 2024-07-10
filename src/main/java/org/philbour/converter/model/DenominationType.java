package org.philbour.converter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DenominationType {
    @JsonProperty("coin")
    COIN,
    @JsonProperty("note")
    NOTE

}
