package com.tingshulien.account.controller;

import com.tingshulien.account.dto.CustomerDetailDto;
import com.tingshulien.account.dto.ErrorResponseDto;
import com.tingshulien.account.service.CustomerService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Customer REST API",
    description = "REST API for bank to FETCH customer details"
)
@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @Operation(
      summary = "Fetch Customer Detail REST API",
      description = "REST API to fetch Customer detail from Bank"
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
  @Retry(name = "fetch-customer-detail")
  @RateLimiter(name = "fetch-customer-detail")
  @GetMapping("/v1/fetchCustomerDetail")
  public ResponseEntity<CustomerDetailDto> fetchCustomerDetail(
      @RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits") @Parameter(example = "0910123456") String mobileNumber) {
    CustomerDetailDto customerDetailDto = customerService.fetchCustomerDetail(mobileNumber);
    return ResponseEntity.ok(customerDetailDto);
  }

}
