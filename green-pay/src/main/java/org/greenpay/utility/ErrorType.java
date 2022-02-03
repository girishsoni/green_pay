package org.greenpay.utility;

import org.greenpay.dto.ExceptionDto;

import java.io.Serializable;

public interface ErrorType extends Serializable {
    String getCode();
    String getMessage();
    Severity getSeverity();
    int getHttpStatusCode();

    default ExceptionDto toExceptionDto(){
        final ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setCode(this.getCode());
        exceptionDto.setMessage(this.getMessage());
        exceptionDto.setSeverity(this.getSeverity().getText());
        return exceptionDto;
    }

}
