package ro.fiipractic.mycinema.controllers;

import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
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
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(CinemaController.class.getName());

    @GetMapping
    public List<CinemaDto> getAllCinemas() {
        logger.info("CinemaController method getAllCinemas called");
        List<CinemaDto> cinemaDtos = new ArrayList<>();

        for (Cinema entity : cinemaService.getAllCinemas()) {
            CinemaDto map = modelMapper.map(entity, CinemaDto.class);
            cinemaDtos.add(map);
        }
        return cinemaDtos;
    }

    @GetMapping(value = "/filter")
    public List<CinemaDto> getCinemasByMovieRoomId(@RequestParam("capacity") Integer capacity) {
        logger.info("CinemaController method getCinemasByMovieRoomId called with capacity " + capacity);
        List<CinemaDto> cinemaDtos = new ArrayList<>();

        for (Cinema entity : cinemaService.getCinemasByMovieRoomsCapacity(capacity)) {
            CinemaDto map = modelMapper.map(entity, CinemaDto.class);
            cinemaDtos.add(map);
        }
        return cinemaDtos;
    }

    @GetMapping("/{id}")
    public CinemaDto getCinemaById(@PathVariable Long id) throws NotFoundException {
        logger.info("CinemaController method getCinemaById called with id " + id);
        Cinema entity = cinemaService.getCinemaById(id);

        return modelMapper.map(entity, CinemaDto.class);
    }

    @PostMapping
    public ResponseEntity<Cinema> saveCinema(@RequestBody CinemaDto cinemaDto) throws URISyntaxException {
        logger.info("CinemaController method saveCinema called for cinema " + cinemaDto.toString());
        Cinema cinema = cinemaService.saveCinema(modelMapper.map(cinemaDto, Cinema.class));
        return ResponseEntity.created(new URI("/api/cinemas/" + cinema.getId())).body(cinema);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cinema> updateCinema(@PathVariable("id") Long id, @RequestBody CinemaDto cinemaToUpdateDto) throws BadRequestException, NotFoundException {
        logger.info("CinemaController updateCinema method called for cinema with id= " + id + " and cinema " + cinemaToUpdateDto.toString());
        if (!id.equals(cinemaToUpdateDto.getId())) {
            logger.info("BadRequestException exception thrown by CinemaController updateCinema method");
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + cinemaToUpdateDto.getId() + " from RequestBody");
        }
        Cinema cinema = cinemaService.updateCinema(modelMapper.map(cinemaToUpdateDto,Cinema.class));
        return ResponseEntity.ok(cinema);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("CinemaController method deleteById called with id " + id);
        cinemaService.deleteCinemaById(id);
    }
}
