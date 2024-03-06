package com.tingshulien.account.mapper;

import com.tingshulien.account.dto.AccountDto;
import com.tingshulien.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  AccountDto toDto(Account account);

  @Mapping(target = "createAt", ignore = true)
  @Mapping(target = "createBy", ignore = true)
  @Mapping(target = "updateAt", ignore = true)
  @Mapping(target = "updateBy", ignore = true)
  @Mapping(target = "customerId", ignore = true)
  Account toEntity(AccountDto accountDto);

}
