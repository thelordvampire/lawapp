package com.app.law.entity.mapper;

import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMessageMapper {

    ChatMessageMapper INSTANCE = Mappers.getMapper( ChatMessageMapper.class );

    @Mappings({
        @Mapping(source = "userId", target = "serverUserId")
    })
    ChatMessageDTO toDTO(ChatMessage message);
}
