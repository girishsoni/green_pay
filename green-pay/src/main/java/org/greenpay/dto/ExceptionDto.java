package org.greenpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class ExceptionDto {
    private String code;
    private String message;
    private String severity;
    private String source;
    private String target;

    private Map<String,String> parameters = new HashMap<>();

    public ExceptionDto withParameters(){
        parameters = new HashMap<>();
        return this;
    }

    @Override
    public String toString(){
        return String.format("code=%s; message=%s; source=%s; target=%s",
                code, message, source, target) +
                parameters.entrySet().stream()
                        .map(entry -> String.format(" %s=%s;", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(""));
    }
}
