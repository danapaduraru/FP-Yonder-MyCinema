package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id) throws NotFoundException;
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
}
