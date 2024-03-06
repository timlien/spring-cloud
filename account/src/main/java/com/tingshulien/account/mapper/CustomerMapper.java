package com.tingshulien.account.mapper;

import com.tingshulien.account.dto.CustomerDto;
import com.tingshulien.account.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  @Mapping(target = "accountDto", ignore = true)
  CustomerDto toDto(Customer customer);

  @Mapping(target = "createAt", ignore = true)
  @Mapping(target = "createBy", ignore = true)
  @Mapping(target = "updateAt", ignore = true)
  @Mapping(target = "updateBy", ignore = true)
  @Mapping(target = "customerId", ignore = true)
  Customer toEntity(CustomerDto customerDto);

}
