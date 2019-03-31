package ro.fiipractic.mycinema.services;

import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    Cinema saveCinema(Cinema cinema);

    List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity);

    void deleteCinemaById(Long id);
}
