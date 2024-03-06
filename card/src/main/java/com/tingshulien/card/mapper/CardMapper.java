package com.tingshulien.card.mapper;

import com.tingshulien.card.dto.CardDto;
import com.tingshulien.card.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

  CardDto toDto(Card card);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "cardId", ignore = true)
  Card toEntity(CardDto cardDto);

}
