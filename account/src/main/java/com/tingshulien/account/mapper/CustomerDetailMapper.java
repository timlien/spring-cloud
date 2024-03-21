package com.tingshulien.account.mapper;

import com.tingshulien.account.dto.CustomerDetailDto;
import com.tingshulien.account.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDetailMapper {

  @Mapping(target = "accountDto", ignore = true)
  @Mapping(target = "loanDto", ignore = true)
  @Mapping(target = "cardDto", ignore = true)
  CustomerDetailDto toDto(Customer customer);

}
