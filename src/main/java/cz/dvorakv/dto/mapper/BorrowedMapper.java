package cz.dvorakv.dto.mapper;

import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.entity.BorrowedEntity;
import cz.dvorakv.entity.CustomerEntity;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.service.BorrowedService;
import cz.dvorakv.service.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author dvora
 * @since 13.7.2024
 */
@Mapper(componentModel = "spring", uses = { BorrowedService.class, MovieMapper.class, CustomerMapper.class })
public interface BorrowedMapper {

  BorrowedDto toDto(BorrowedEntity source);

  @Mapping(target = "customer", expression = "java(mapCustomer(source.getCustomerID(), service))")
  @Mapping(target = "movie", expression = "java(mapMovie(source.getMovieID(), service))")
  BorrowedEntity toEntity(BorrowedDto source, BorrowedService service);

  default CustomerEntity mapCustomer(Long source, BorrowedService service) {
    return service.mapCustomer(source);
  }

  default MovieEntity mapMovie(Long source, BorrowedService service) {
    return service.mapMovie(source);
  }

}

