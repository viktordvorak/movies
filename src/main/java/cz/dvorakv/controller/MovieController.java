package cz.dvorakv.controller;

import cz.dvorakv.configuration.GenreConfiguration;
import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import cz.dvorakv.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private GenreConfiguration genreConfiguration;

    //@Secured("ROLE_ADMIN")
    @PostMapping("/movie")
    public MovieDto addMovie(@RequestBody final MovieDto dto) {
        return service.addMovie(dto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/movie/{id}")
    public MovieDto editMovie(@RequestBody final MovieDto dto, @PathVariable final Long id) {
        return service.updateMovie(dto, id);
    }

    @GetMapping("/movie/{id}")
    public MovieDto getMovie(@PathVariable final Long id) {
        return service.getMovie(id);
    }

    @GetMapping("/movie")
    public List<MovieDto> getMovies(final MovieFilter filter) {
        return service.getAllMovies(filter);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/movie/{id}")
    public MovieDto deleteMovie(@PathVariable final Long id) {
        return service.deleteMovie(id);
    }

    @GetMapping({"/genres", "/genres/"})
    public List<String> getGenres() {
        return genreConfiguration.getGenres();
    }

}
