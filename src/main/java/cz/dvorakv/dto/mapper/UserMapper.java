package cz.dvorakv.dto.mapper;

import cz.dvorakv.dto.UserDto;
import cz.dvorakv.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity entity);

}
