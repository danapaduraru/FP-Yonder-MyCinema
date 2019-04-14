package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/movie-instances")
public class MovieInstanceController {

    @Autowired
    MovieInstanceService movieInstanceService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(MovieInstanceController.class.getName());

    @GetMapping
    public List<MovieInstanceDto> getAllMovieInstances() {
        logger.info("MovieInstanceController getAllMovieInstances method called");
        List<MovieInstanceDto> list = new ArrayList<>();

        for (MovieInstance entity : movieInstanceService.getAllMovieInstances()) {
            MovieInstanceDto map = modelMapper.map(entity, MovieInstanceDto.class);
            list.add(map);
        }
        return list;
    }

    @GetMapping("/{id}")
    public MovieInstanceDto getMovieInstanceById(@PathVariable Long id) throws NotFoundException {
        logger.info("MovieInstanceController getMovieInstanceById method called with id " + id);
        MovieInstance entity = movieInstanceService.getMovieInstanceById(id);
        return modelMapper.map(entity, MovieInstanceDto.class);
    }

    @PostMapping
    public ResponseEntity<MovieInstance> saveMovieInstance(@RequestBody MovieInstanceDto movieInstanceDto) throws URISyntaxException {
        logger.info("MovieInstanceController saveMovieInstance method called for movieInstance " + movieInstanceDto.toString());
        MovieInstance movieInstance = movieInstanceService.saveMovieInstance(modelMapper.map(movieInstanceDto, MovieInstance.class));
        return ResponseEntity.created(new URI("/api/movie-instances/" + movieInstance.getId())).body(movieInstance);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieInstance> updateMovieInstance(@PathVariable("id") Long id, @RequestBody MovieInstanceDto movieInstanceToUpdateDto) throws NotFoundException, BadRequestException {
        logger.info("MovieInstanceController updateMovieInstance method called for movieInstance with id= " + id + " and movieInstance " + movieInstanceToUpdateDto.toString());
        if (!id.equals(movieInstanceToUpdateDto.getId())) {
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + movieInstanceToUpdateDto.getId() + " from RequestBody");
        }
        MovieInstance movieInstance = movieInstanceService.updateMovieInstance(modelMapper.map(movieInstanceToUpdateDto,MovieInstance.class));
        return ResponseEntity.ok(movieInstance);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovieInstance(@PathVariable Long id) {
        logger.info("MovieInstanceController deleteMovieInstance method called with id " + id);
        movieInstanceService.deleteMovieInstance(id);
    }
}
