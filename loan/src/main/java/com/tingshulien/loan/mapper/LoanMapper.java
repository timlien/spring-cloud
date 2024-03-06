package com.tingshulien.loan.mapper;

import com.tingshulien.loan.dto.LoanDto;
import com.tingshulien.loan.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

  LoanDto toDto(Loan loan);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "loanId", ignore = true)
  Loan toEntity(LoanDto loanDto);

}
