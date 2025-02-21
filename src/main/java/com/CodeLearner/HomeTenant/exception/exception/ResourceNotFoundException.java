package com.CodeLearner.HomeTenant.exception.exception;


import com.CodeLearner.HomeTenant.global.I18nConstants;

/**
 * @author Franck Sgen Lecroyant The global Resource Exception to be thrown
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException implements MessageKeyError {
    private final String errorMessageKey;
    private final String descriptionMessageKey;
    private final String injectedMessageKey;

    public ResourceNotFoundException(String injectedMessageKey, String exceptionMessageDescriptionKey) {
        this.injectedMessageKey = injectedMessageKey;
        this.errorMessageKey = I18nConstants.RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE_ERROR_KEY;
        this.descriptionMessageKey = exceptionMessageDescriptionKey;
    }

    @Override
    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    @Override
    public String getDescriptionMessageKey() {
        return descriptionMessageKey;
    }

    public String getInjectedMessageKey() {
        return injectedMessageKey;
    }
}
