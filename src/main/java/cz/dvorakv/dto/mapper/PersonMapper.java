package cz.dvorakv.dto.mapper;

import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDto(PersonEntity entity);
    PersonEntity toEntity(PersonDto dto);

}
