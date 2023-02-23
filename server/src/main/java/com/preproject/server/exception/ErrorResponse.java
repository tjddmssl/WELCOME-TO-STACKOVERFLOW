package com.preproject.server.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ErrorResponse {

  private final List<FieldError> fieldErrors;
  private final List<ConstraintViolationError> constraintViolationErrors;
  private final BusinessLogicException businessLogicException;

  private ErrorResponse(List<FieldError> fieldErrors,
      List<ConstraintViolationError> constraintViolationErrors,
      BusinessLogicException businessLogicException) {
    this.fieldErrors = fieldErrors;
    this.constraintViolationErrors = constraintViolationErrors;
    this.businessLogicException = businessLogicException;
  }

  public static ErrorResponse of(BindingResult bindingResult) {
    return new ErrorResponse(FieldError.of(bindingResult), null, null);
  }

  public static ErrorResponse of(Set<ConstraintViolation<?>> constraintViolations) {
    return new ErrorResponse(null, ConstraintViolationError.of(constraintViolations),
        null);
  }

  public static ErrorResponse of(BusinessLogicException businessLogicException) {
    return new ErrorResponse(null, null, businessLogicException);
  }

  @Getter
  @AllArgsConstructor
  public static class FieldError {

    private String field;
    private Object rejectedValue;
    private String reason;

    public static List<FieldError> of(BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrorList = bindingResult.getFieldErrors();
      return fieldErrorList.stream().map(fieldError -> new FieldError(
          fieldError.getField(),
          fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue(),
          fieldError.getDefaultMessage()
      )).collect(Collectors.toList());
    }
  }

  @Getter
  @AllArgsConstructor
  public static class ConstraintViolationError {

    private String propertyPath;
    private Object rejectedValue;
    private String reason;

    public static List<ConstraintViolationError> of(
        Set<ConstraintViolation<?>> constraintViolations) {
      return constraintViolations.stream().map(constraintViolation -> new ConstraintViolationError(
          constraintViolation.getPropertyPath().toString(),
          constraintViolation.getInvalidValue().toString(),
          constraintViolation.getMessage()
      )).collect(Collectors.toList());
    }
  }
}
