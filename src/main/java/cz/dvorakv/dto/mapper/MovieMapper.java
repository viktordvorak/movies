package cz.dvorakv.dto.mapper;

import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.service.MovieService;
import org.hibernate.annotations.Source;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dvora
 * @since 11.11.2023
 */
@Mapper(componentModel = "spring", uses=MovieService.class)
public interface MovieMapper {

    @Mapping(target = "actorIDs", expression = "java(getActorIds(source))")
    @Mapping(target = "directorID", source = "director.id")
    @Mapping(target = "dateAdded", ignore = true)
    MovieDto toDto(MovieEntity source);

    @Mapping(target = "actors", expression = "java(getActors(source, service))")
    @Mapping(target = "director", expression = "java(getDirector(source, service))")
    @Mapping(target = "dateAdded", ignore = true)
    MovieEntity toEntity(MovieDto source,  MovieService service);

    MovieEntity updateEntity(MovieDto source, @MappingTarget MovieEntity target);

    default List<Long> getActorIds(MovieEntity source) {
        return source.getActors().stream()
                .map(PersonEntity::getId)
                .collect(Collectors.toList());
    }

    default List<PersonEntity> getActors(MovieDto source, MovieService service) {
        return service.mapActorsToMovie(source);
    }

    default PersonEntity getDirector(MovieDto source, MovieService service) {
        return service.mapDirectorToMovie(source);
    }

//    @AfterMapping
//    default void map(@MappingTarget MovieEntity target, MovieDto source, @Context MovieService service) {
//        target.setDirector(service.mapDirectorToMovie(source));
//        target.setActors(service.mapActorsToMovie(source));
//    }



}
