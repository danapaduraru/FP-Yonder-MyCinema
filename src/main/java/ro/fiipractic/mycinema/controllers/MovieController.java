package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieDto;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(MovieController.class.getName());

    @GetMapping
    public List<MovieDto> getAllMovies() {
        logger.info("MovieController getAllMovies method called");
        List<MovieDto> movieDtos = new ArrayList<>();

        for (Movie entity : movieService.getAllMovies()) {
            MovieDto map = modelMapper.map(entity, MovieDto.class);
            movieDtos.add(map);
        }
        return movieDtos;
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) throws NotFoundException {
        logger.info("MovieController getMovieById method called with id " + id);
        Movie entity = movieService.getMovieById(id);
        return modelMapper.map(entity, Movie.class);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDto movieDto) throws URISyntaxException {
        logger.info("MovieController saveMovie method called for movie " + movieDto.toString());
        Movie movie = movieService.saveMovie(modelMapper.map(movieDto, Movie.class));
        return ResponseEntity.created(new URI("/api/movies/" + movie.getId())).body(movie);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovie(@PathVariable Long id) {
        logger.info("MovieController deleteMovie method called with id " + id);
        movieService.deleteMovie(id);
    }
}
