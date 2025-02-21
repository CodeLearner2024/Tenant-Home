package com.CodeLearner.HomeTenant.exception.handler;

import com.CodeLearner.HomeTenant.exception.exception.AnnotationNotSupportedException;
import com.CodeLearner.HomeTenant.exception.exception.ApiCallException;
import com.CodeLearner.HomeTenant.exception.exception.ResourceNotFoundException;
import com.CodeLearner.HomeTenant.exception.exception.UniquenessEntryBreachException;
import com.CodeLearner.HomeTenant.exception.message.ApiExceptionMessage;
import com.CodeLearner.HomeTenant.exception.message.ArgumentNotValidExceptionMessage;
import com.CodeLearner.HomeTenant.exception.message.ExceptionValueMessageStructure;
import com.CodeLearner.HomeTenant.global.I18nConstants;
import com.CodeLearner.HomeTenant.global.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocalizationService localizationService;

    @Autowired
    public GlobalExceptionHandler(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(),
                this.localizationService.getLocalizedMessage(exception.getErrorMessageKey()),
                webRequest.getDescription(false),
                String.format(this.localizationService.getLocalizedMessage(exception.getDescriptionMessageKey()), this.localizationService.getLocalizedMessage(exception.getInjectedMessageKey())),
                httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = IllegalCallerException.class)
    public ResponseEntity<ApiExceptionMessage> handleUnsupportedMethodCall(IllegalCallerException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(),
                I18nConstants.ILLEGAL_CALLER_ERROR_MESSAGE_DESCRIPTION, webRequest.getDescription(false), exception.getMessage(), httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiExceptionMessage> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(), "",
                webRequest.getDescription(false), exception.getMessage(), httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArgumentNotValidExceptionMessage exceptionMessage = new ArgumentNotValidExceptionMessage();
        Map<Object, List<ExceptionValueMessageStructure>> fieldsErrors = new HashMap<>();

        List<ExceptionValueMessageStructure> errorDetails = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            exceptionMessage.setDescription(fieldError.getDefaultMessage());
            exceptionMessage.setPath(request.getDescription(false));
            errorDetails.add(new ExceptionValueMessageStructure(fieldError.getField(), fieldError.getDefaultMessage(),
                    fieldError.getRejectedValue()));

        });
        fieldsErrors.put("details", errorDetails);
        exceptionMessage.setFieldsErrors(fieldsErrors);
        return new ResponseEntity<>(exceptionMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(),
                "",
                request.getDescription(false), "Wrong request to the wrong path", httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiExceptionMessage> handleConstrainteViolationException(DataIntegrityViolationException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(),
                "Data Integrity Exception", webRequest.getDescription(false),
                "Les valeures comme code, designation doivent etre uniques", httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = ApiCallException.class)
    public ResponseEntity<ApiExceptionMessage> handleApiCallException(ApiCallException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.EXPECTATION_FAILED;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(), "",
                webRequest.getDescription(false), exception.getMessage(), httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = UnsupportedOperationException.class)
    public ResponseEntity<ApiExceptionMessage> handleUnsupportedOperationException(UnsupportedOperationException exception, WebRequest webRequest) {

        HttpStatus httpStatus = HttpStatus.EXPECTATION_FAILED;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(), "",
                webRequest.getDescription(false), exception.getMessage(), httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = AnnotationNotSupportedException.class)
    public ResponseEntity<ApiExceptionMessage> handleAnnotationNotSupportedExceptionException(AnnotationNotSupportedException exception, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(LocalDateTime.now(), "",
                webRequest.getDescription(false), exception.getMessage(), httpStatus);
        return new ResponseEntity<ApiExceptionMessage>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value = UniquenessEntryBreachException.class)
    public ResponseEntity<UniquenessEntryBreachException.ExceptionDetails> handleDataEntryUniquenessBreachException(UniquenessEntryBreachException exception, WebRequest webRequest) {
        return new ResponseEntity<>(exception.getPropertyDetails(), HttpStatus.EXPECTATION_FAILED);
    }
}
