package ro.fiipractic.mycinema.controllers;

import javassist.NotFoundException;
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

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<ReservationDto> getAllReservations() {

        List<ReservationDto> reservationDtos = new ArrayList<>();

        for(Reservation entity : reservationService.getAllReservations()) {
            ReservationDto map = modelMapper.map(entity, ReservationDto.class);
            reservationDtos.add(map);
        }
        return reservationDtos;
    }

    @GetMapping("/{id}")
    public ReservationDto getReservation(@PathVariable Long id) throws NotFoundException {
        Reservation entity = reservationService.getReservationById(id);

        return modelMapper.map(entity, ReservationDto.class);
    }

    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@RequestBody ReservationDto reservationDto) throws URISyntaxException {
        Reservation reservation = reservationService.saveReservation(modelMapper.map(reservationDto, Reservation.class));
        return ResponseEntity.created(new URI("/api/reservations/" + reservation.getId())).body(reservation);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
