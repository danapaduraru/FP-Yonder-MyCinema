package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieDto;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDto movieDto) throws URISyntaxException {
        Movie movie = movieService.saveMovie(modelMapper.map(movieDto,Movie.class));
        return ResponseEntity.created(new URI("api/movies/" + movie.getId())).body(movie);
    }
}
