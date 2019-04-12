package ro.fiipractic.mycinema.controllers;

import ro.fiipractic.mycinema.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.ReservationDto;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.services.ReservationService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(ReservationController.class.getName());

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        logger.info("ReservationController getAllReservations method called");
        List<ReservationDto> reservationDtos = new ArrayList<>();

        for(Reservation entity : reservationService.getAllReservations()) {
            ReservationDto map = modelMapper.map(entity, ReservationDto.class);
            reservationDtos.add(map);
        }
        return reservationDtos;
    }

    @GetMapping("/{id}")
    public ReservationDto getReservation(@PathVariable Long id) throws NotFoundException {
        logger.info("ReservationController getReservation method called with id " + id);
        Reservation entity = reservationService.getReservationById(id);

        return modelMapper.map(entity, ReservationDto.class);
    }

    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@RequestBody ReservationDto reservationDto) throws URISyntaxException {
        logger.info("ReservationController saveReservation method called for reservation " + reservationDto.toString());
        Reservation reservation = reservationService.saveReservation(modelMapper.map(reservationDto, Reservation.class));
        return ResponseEntity.created(new URI("/api/reservations/" + reservation.getId())).body(reservation);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReservation(@PathVariable Long id) {
        logger.info("ReservationController deleteReservation method called with id " + id);
        reservationService.deleteReservation(id);
    }
}
