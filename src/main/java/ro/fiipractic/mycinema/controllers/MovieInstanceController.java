package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie-instances")
public class MovieInstanceController {

    @Autowired
    MovieInstanceService movieInstanceService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<MovieInstanceDto> getAllMovieInstances(){
        List<MovieInstanceDto> list = new ArrayList<>();

        for (MovieInstance entity : movieInstanceService.getAllMovieInstances()) {
            MovieInstanceDto map = modelMapper.map(entity, MovieInstanceDto.class);
            list.add(map);
        }
        return list;
    }

    @GetMapping("/{id}")
    public MovieInstanceDto getMovieInstanceById(@PathVariable Long id) {
        MovieInstance entity = movieInstanceService.getMovieInstanceById(id);

        return modelMapper.map(entity, MovieInstanceDto.class);
    }

    @PostMapping
    public ResponseEntity<MovieInstance> saveMovieInstance(@RequestBody MovieInstanceDto movieInstanceDto) throws URISyntaxException {
        MovieInstance movieInstance = movieInstanceService.saveMovieInstance(modelMapper.map(movieInstanceDto,MovieInstance.class));
        return ResponseEntity.created(new URI("/api/movie-instances/" + movieInstance.getId())).body(movieInstance);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovieInstance(@PathVariable Long id) {
        movieInstanceService.deleteMovieInstance(id);
    }
}
