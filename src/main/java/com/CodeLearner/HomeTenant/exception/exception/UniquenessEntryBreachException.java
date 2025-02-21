package com.CodeLearner.HomeTenant.exception.exception;

import java.util.List;

/**
 * @author Franck Sgen Lecroyant
 * created on 8/29/2023
 */
public class UniquenessEntryBreachException extends RuntimeException {

    private final String message;
    private final ExceptionDetails exceptionDetails;

    public UniquenessEntryBreachException(String message, ExceptionDetails exceptionDetails) {
        this.message = message;
        this.exceptionDetails = exceptionDetails;
    }

    public static ExceptionDetails getDefaultExceptionDetails(List<String> uniqueFields) {
        return new ExceptionDetails(
                "The following fields should be unique", uniqueFields);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ExceptionDetails getPropertyDetails() {
        return exceptionDetails;
    }

    public static class ExceptionDetails {
        private final String errorMessage;
        private final List<String> fields;

        public ExceptionDetails(String errorMessage, List<String> fields) {
            this.errorMessage = errorMessage;
            this.fields = fields;
        }


        public String getErrorMessage() {
            return errorMessage;
        }

        public List<String> getFields() {
            return fields;
        }
    }
}
