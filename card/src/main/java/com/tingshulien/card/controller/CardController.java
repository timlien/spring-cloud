package com.tingshulien.card.controller;

import com.tingshulien.card.constant.CardConstants;
import com.tingshulien.card.dto.CardDto;
import com.tingshulien.card.dto.ErrorResponseDto;
import com.tingshulien.card.dto.ResponseDto;
import com.tingshulien.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardController {

  private final CardService cardService;

  @Operation(
      summary = "Create Card REST API",
      description = "REST API to create new Card inside Bank"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "HTTP Status CREATED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createCard(
      @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
    cardService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Card Details REST API",
      description = "REST API to fetch card details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  })
  @GetMapping("/fetch")
  public ResponseEntity<CardDto> fetchCard(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(cardService.fetchCard(mobileNumber));
  }

  @Operation(
      summary = "Update Card Details REST API",
      description = "REST API to update card details based on a card number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto) {
    if (!cardService.updateCard(cardDto)) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
  }

  @Operation(
      summary = "Delete Card Details REST API",
      description = "REST API to delete Card details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteCard(
      @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
    if (!cardService.deleteCard(mobileNumber)) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
  }

}
