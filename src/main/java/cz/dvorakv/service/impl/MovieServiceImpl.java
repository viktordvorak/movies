package cz.dvorakv.service.impl;

import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.dto.mapper.MovieMapper;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.entity.repository.MovieRepository;
import cz.dvorakv.entity.repository.PersonRepository;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import cz.dvorakv.entity.repository.specification.MovieSpecification;
import cz.dvorakv.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MovieMapper mapper;

    @Override
    public MovieDto addMovie(final MovieDto dto) {
        val entity = mapper.toEntity(dto, this);

//        val actors= personRepository.findAllById(dto.getActorIDs());
//        entity.setActors(actors);
//        val director = personRepository.getReferenceById(dto.getDirectorID());
//        entity.setDirector(director);

        val savedEntity = movieRepository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public MovieDto updateMovie(final MovieDto dto, final Long id) {
        dto.setId(id);
        val entity = movieRepository.getReferenceById(id);
        val updatedEntity = mapper.toEntity(dto, this);

//        for (Long actorId : dto.getActorIDs()) {
//            entity.getActors().add(personRepository.getReferenceById(actorId));
//        }
//        entity.setDirector(personRepository.getReferenceById(dto.getDirectorID()));
        val savedEntity = movieRepository.save(updatedEntity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public MovieDto getMovie(final Long id) {
        return mapper.toDto(movieRepository.getReferenceById(id));
    }

    @Override
    public List<MovieDto> getAllMovies(final MovieFilter filter) {
        val movieSpecification = new MovieSpecification(filter);
        return movieRepository.findAll(movieSpecification).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto deleteMovie(final Long id) {
        val movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        val model = mapper.toDto(movie);
        movieRepository.delete(movie);
        return model;
    }

    @Override
    public PersonEntity mapDirectorToMovie(final MovieDto source) {
        return personRepository.getReferenceById(source.getDirectorID());
    }

    @Override
    public List<PersonEntity> mapActorsToMovie(final MovieDto source) {
        return personRepository.findAllById(source.getActorIDs());
    }


}
