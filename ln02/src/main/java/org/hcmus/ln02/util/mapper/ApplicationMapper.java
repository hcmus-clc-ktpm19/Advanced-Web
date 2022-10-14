package org.hcmus.ln02.util.mapper;

import org.hcmus.ln02.model.dto.ActorDto;
import org.hcmus.ln02.model.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

  ActorDto toActorDto(Actor entity);

  @Mapping(target = "lastUpdate", ignore = true)
  Actor toActorEntity(ActorDto dto);
}
