package org.example.ccms.mapper;

import org.example.ccms.model.card.CardDto;
import org.example.ccms.model.card.CardEntity;
import org.example.ccms.model.user.UserDto;
import org.example.ccms.model.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardEntity createCardEntity(CardDto source);
}
