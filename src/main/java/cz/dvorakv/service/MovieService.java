package cz.dvorakv.service;

import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dvora
 * @since 11.11.2023
 */
public interface MovieService {

    MovieDto addMovie(MovieDto dto);

    MovieDto updateMovie(MovieDto dto, Long id);

    MovieDto getMovie(Long id);

    List<MovieDto> getAllMovies(MovieFilter filter);

    MovieDto deleteMovie(Long id);

    List<PersonEntity> mapActorsToMovie(final MovieDto source);

    PersonEntity mapDirectorToMovie(final MovieDto source);



}
