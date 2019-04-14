package ro.fiipractic.mycinema.services;

import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAllCinemas();

    List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity);

    Cinema getCinemaById(Long id) throws NotFoundException;

    Cinema saveCinema(Cinema cinema);

    Cinema updateCinema(Cinema updatedCinema);

    void deleteCinemaById(Long id);
}
