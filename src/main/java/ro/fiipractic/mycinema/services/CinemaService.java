package ro.fiipractic.mycinema.services;

import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAllCinemas();

    Cinema saveCinema(Cinema cinema);

    List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity);

    Cinema getCinemaById(Long id) throws NotFoundException;

    void deleteCinemaById(Long id);
}
