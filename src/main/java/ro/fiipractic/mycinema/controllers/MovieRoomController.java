package ro.fiipractic.mycinema.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieRoomDto;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/movie-room")
public class MovieRoomController {

    @Autowired
    MovieRoomService movieRoomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MovieRoomDto> getAllMovieRooms() {
        List<MovieRoomDto> movieRoomDtos = new ArrayList<>();

        for (MovieRoom entity : movieRoomService.getAllMovieRooms()) {
            MovieRoomDto map = modelMapper.map(entity, MovieRoomDto.class);
            movieRoomDtos.add(map);
        }
        return movieRoomDtos;
    }

    @GetMapping(value = "/filter/{cinemaId}")
    public List<MovieRoomDto> getAllMovieRoomsByCinemaId(@PathVariable Long cinemaId) {
        List<MovieRoomDto> movieRoomDtos = new ArrayList<>();

        for (MovieRoom entity : movieRoomService.getAllMovieRoomsByCinemaId(cinemaId)) {
            MovieRoomDto map = modelMapper.map(entity, MovieRoomDto.class);
            movieRoomDtos.add(map);
        }
        return movieRoomDtos;
    }

    @GetMapping("/{id}")
    public MovieRoomDto getMovieRoomById(@PathVariable Long id) {
        MovieRoom entity = movieRoomService.getMovieRoomById(id);

        return modelMapper.map(entity,MovieRoomDto.class);
    }

    @PostMapping
    public ResponseEntity<MovieRoom> saveMovieRoom(@RequestBody MovieRoomDto movieRoomDto) throws URISyntaxException {
        MovieRoom movieRoom = movieRoomService.saveMovieRoom(modelMapper.map(movieRoomDto, MovieRoom.class));
        return ResponseEntity.created(new URI("api/movie-rooms/" + movieRoom.getId())).body(movieRoom);
    }

    @DeleteMapping(value="/{id}")
    public void deleteMovieRoom(@PathVariable Long id)
    {
        movieRoomService.deleteMovieRoom(id);
    }
}
