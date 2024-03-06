package com.tingshulien.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
    name = "Account",
    description = "Schema to hold account information"
)
@Data
public class AccountDto {

  @Schema(
      description = "Account number of the customer",
      example = "1234567890"
  )
  @NotEmpty(message = "Account number cannot be empty")
  @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be 10 digits")
  private Long accountNumber;

  @Schema(
      description = "Account type of the customer",
      example = "Savings"
  )
  @NotEmpty(message = "Account type cannot be empty")
  private String accountType;

  @Schema(
      description = "Branch address of the customer",
      example = "123 Main St, San Francisco, CA 94122"
  )
  @NotEmpty(message = "Branch address cannot be empty")
  private String branchAddress;

}
