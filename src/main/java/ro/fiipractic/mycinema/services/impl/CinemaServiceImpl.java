package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.services.CinemaService;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    private static final Logger logger = Logger.getLogger(CinemaServiceImpl.class.getName());

    @Override
    public List<Cinema> getAllCinemas() {
        logger.info("CinemaService getAllCinemas method called");
        return cinemaRepository.findAll();
    }

    @Override
    public List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity) {
        logger.info("CinemaService getCinemasByMovieRoomsCapacity method called for capacity " + capacity);
        return cinemaRepository.getCinemasByMovieRoomCapacity(capacity);
    }

    public Cinema getCinemaById(Long id) throws NotFoundException {
        logger.info("CinemaService getCinemaById method called for id " + id);
        return cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cinema with id=%s was not found.", id)));
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        logger.info("CinemaService saveCinema method called");
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinemaById(Long id) {
        logger.info("CinemaService deleteCinemaById method called with id " + id);
        cinemaRepository.deleteById(id);
    }
}
