/**
 *
 */
package com.CodeLearner.HomeTenant.exception.exception;


import com.CodeLearner.HomeTenant.global.I18nConstants;

/**
 * @author Franck Sgen Lecroyant
 */
@SuppressWarnings("serial")
public class ApiCallException extends RuntimeException implements MessageKeyError {

    private final String message;
    private final String errorMessage;

    public ApiCallException(String message) {
        this.errorMessage = I18nConstants.API_CALL_ERROR_MESSAGE_ERROR;
        this.message = message;
    }

    @Override
    public String getErrorMessageKey() {
        return this.message;
    }

    @Override
    public String getDescriptionMessageKey() {
        return this.errorMessage;
    }
}
