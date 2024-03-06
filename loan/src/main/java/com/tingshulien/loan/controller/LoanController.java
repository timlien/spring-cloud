package com.tingshulien.loan.controller;

import com.tingshulien.loan.constant.LoanConstants;
import com.tingshulien.loan.dto.ErrorResponseDto;
import com.tingshulien.loan.dto.LoanDto;
import com.tingshulien.loan.dto.ResponseDto;
import com.tingshulien.loan.service.LoanService;
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
public class LoanController {

  private final LoanService loanService;

  @Operation(
      summary = "Create Loan REST API",
      description = "REST API to create new loan inside Bank"
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
  }
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createLoan(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    loanService.createLoan(mobileNumber);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Loan Details REST API",
      description = "REST API to fetch loan details based on a mobile number"
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
  }
  )
  @GetMapping("/fetch")
  public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    LoanDto loanDto = loanService.fetchLoan(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(loanDto);
  }

  @Operation(
      summary = "Update Loan Details REST API",
      description = "REST API to update loan details based on a loan number"
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
  }
  )
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto) {
    boolean isUpdated = loanService.updateLoan(loanDto);
    if (isUpdated) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
      summary = "Delete Loan Details REST API",
      description = "REST API to delete Loan details based on a mobile number"
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
  }
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    boolean isDeleted = loanService.deleteLoan(mobileNumber);
    if (isDeleted) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
    }
  }

}
