package com.tingshulien.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Schema(
    name = "ErrorResponse",
    description = "Schema to hold error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

  @Schema(
      description = "API path invoke by client"
  )
  private String apiPath;

  @Schema(
      description = "HTTP status code of the error response",
      example = "500"
  )
  private HttpStatus errorCode;

  @Schema(
      description = "Error message of the error response"
  )
  private String errorMessage;

  @Schema(
      description = "Time of the error response"
  )
  private LocalDateTime errorTime;

}
