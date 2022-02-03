package org.greenpay.utility;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Severity implements Serializable {
    CRITICAL("critical"),
    ERROR("error"),
    WARNING("warning"),
    INFO("info");

    private String text;

    @JsonCreator
    public static Severity fromText(final String text){
        return Stream.of(Severity.values())
                .filter(severity -> severity.text.equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getText() {return this.text;}
}