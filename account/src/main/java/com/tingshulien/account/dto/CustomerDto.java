package com.tingshulien.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold customer and account information"
)
public class CustomerDto {

  @Schema(
      description = "Name of the customer",
      example = "Tim Lien"
  )
  @NotEmpty(message = "Customer name cannot be empty")
  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
  private String name;

  @Schema(
      description = "Email address of the customer",
      example = "tim@gmail.com"
  )
  @NotEmpty(message = "Email cannot be empty")
  @Email(message = "Email address should be a valid value")
  private String email;

  @Schema(
      description = "Mobile number of the customer",
      example = "0900123456"
  )
  @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
  private String mobileNumber;

  @Schema(
      description = "Account details of the customer"
  )
  private AccountDto accountDto;

}
