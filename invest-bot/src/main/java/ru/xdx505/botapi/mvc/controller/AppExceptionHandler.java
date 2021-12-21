package ru.xdx505.botapi.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Profile("!dev")
@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {
  private final ObjectMapper objectMapper;

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<String> handleReportException(HttpServletRequest req, Exception ex) throws JsonProcessingException {
    var error = new ApiError(
      HttpStatus.BAD_REQUEST.value(),
      "Ошибка сервера",
      List.of(ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex)));
    var json = this.objectMapper.writeValueAsString(error);
    log.error(error.toString());
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .contentType(MediaType.APPLICATION_JSON)
      .body(json);
  }

  @Value
  private static class ApiError {
    int statusCode;
    String message;
    List<String> errors;
  }
}
