package org.greenpay.utility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GenericErrorType implements ErrorType {
    ASYNC_TIMEOUT("AsyncTimeout", "TimeoutException", Severity.CRITICAL, 500);

    private String code;
    private String message;
    private Severity severity;
    private int httpStatusCode;
}
