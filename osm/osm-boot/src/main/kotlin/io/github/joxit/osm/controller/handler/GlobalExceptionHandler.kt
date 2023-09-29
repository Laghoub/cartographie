package io.github.joxit.osm.controller.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * C'est ici que vous devez gérer les exceptions qui sont levés. Utilisation de ControllerAdvice et ExceptionHandler.
 */
@ControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(IllegalArgumentException::class)

  fun illegalArgumentException(ex: IllegalArgumentException?): ResponseEntity<Map<String, Any>> {
    val errorDetails = mapOf(
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to "Validation Error",
            "message" to (ex?.message ?: "Invalid request")
    )
    return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
  }
}
