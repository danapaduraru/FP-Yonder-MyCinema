package ro.fiipractic.mycinema.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.CinemaDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.services.CinemaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CinemaDto> getAllCinemas() {
        List<CinemaDto> cinemaDtos = new ArrayList<>();

        for(Cinema entity : cinemaService.getAllCinemas()) {
            CinemaDto map = modelMapper.map(entity, CinemaDto.class);
            cinemaDtos.add(map);
        }
        return cinemaDtos;
    }

    @GetMapping(value = "/filter")
    public List<CinemaDto> getCinemasByMovieRoomId(@RequestParam("capacity") Integer capacity) {
        List<CinemaDto> cinemaDtos = new ArrayList<>();

        for(Cinema entity : cinemaService.getCinemasByMovieRoomsCapacity(capacity)) {
            CinemaDto map = modelMapper.map(entity, CinemaDto.class);
            cinemaDtos.add(map);
        }
        return cinemaDtos;
    }

    @GetMapping("/{id}")
    public CinemaDto getCinemaById(@PathVariable Long id) throws NotFoundException {
        Cinema entity = cinemaService.getCinemaById(id);

        return modelMapper.map(entity, CinemaDto.class);
    }

    @PostMapping
    public ResponseEntity<Cinema> saveCinema(@RequestBody CinemaDto cinemaDto) throws URISyntaxException {
        Cinema cinema = cinemaService.saveCinema(modelMapper.map(cinemaDto, Cinema.class));
        return ResponseEntity.created(new URI("/api/cinemas/" + cinema.getId())).body(cinema);
    }

    @DeleteMapping(value="/{id}")
    public void deleteById(@PathVariable Long id)
    {
        cinemaService.deleteCinemaById(id);
    }
}
