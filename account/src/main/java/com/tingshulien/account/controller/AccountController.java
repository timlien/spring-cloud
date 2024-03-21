package com.tingshulien.account.controller;

import com.tingshulien.account.constants.AccountConstants;
import com.tingshulien.account.dto.CustomerDto;
import com.tingshulien.account.dto.ErrorResponseDto;
import com.tingshulien.account.dto.ResponseDto;
import com.tingshulien.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
    name = "Account REST API",
    description = "CRUD REST API for bank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@Validated
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @Operation(
      summary = "Create Account REST API",
      description = "REST API to create new Customer & Account inside Bank"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status CREATED"
  )
  @PostMapping(path = "/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountService.createAccount(customerDto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Account REST API",
      description = "REST API to fetch Customer & Account details from Bank"
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
  @GetMapping("fetch")
  public ResponseEntity<CustomerDto> fetchAccount(
      @RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits") @Parameter(example = "0910123456") String mobileNumber) {
    CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
    return ResponseEntity
        .ok()
        .body(customerDto);
  }

  @Operation(
      summary = "Update Account REST API",
      description = "REST API to update Customer & Account details in Bank"
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
  public ResponseEntity<ResponseDto> updateAccountDetails(
      @Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountService.updateAccount(customerDto);
    if (!isUpdated) {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
  }

  @Operation(
      summary = "Delete Account REST API",
      description = "REST API to delete Customer & Account details from Bank"
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
  public ResponseEntity<ResponseDto> deleteAccountDetails(
      @RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits") @Parameter(example = "0910123456") String mobileNumber) {
    boolean isDeleted = accountService.deleteAccount(mobileNumber);
    if (!isDeleted) {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
  }

}
