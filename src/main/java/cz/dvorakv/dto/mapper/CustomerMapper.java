package cz.dvorakv.dto.mapper;

import cz.dvorakv.dto.CustomerDto;
import cz.dvorakv.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerDto toDto(CustomerEntity entity);
  CustomerEntity toEntity(CustomerDto dto);

}
