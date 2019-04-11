package ro.fiipractic.mycinema.services.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.repositories.ReservationRepository;
import ro.fiipractic.mycinema.services.ReservationService;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws NotFoundException {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id=%s was not found.", id)));
    }
    
    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
