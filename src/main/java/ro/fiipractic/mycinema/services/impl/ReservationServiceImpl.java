package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.repositories.ReservationRepository;
import ro.fiipractic.mycinema.services.ReservationService;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    private static final Logger logger = Logger.getLogger(ReservationServiceImpl.class.getName());

    @Override
    public List<Reservation> getAllReservations() {
        logger.info("ReservationService getAllReservations method called");
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws NotFoundException {
        logger.info("ReservationService getReservationById method called for id " + id);
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id=%s was not found.", id)));
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        logger.info("ReservationService saveReservation method called");
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation updatedReservation) {
        logger.info("ReservationService updateReservation method called");
        return reservationRepository.save(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        logger.info("ReservationService deleteReservation method called for id " + id);
        reservationRepository.deleteById(id);
    }
}
