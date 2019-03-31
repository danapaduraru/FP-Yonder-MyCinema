package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    Cinema saveCinema(Cinema cinema);

    List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity);

    Cinema getCinemaById(Long id) throws NotFoundException;

    void deleteCinemaById(Long id);
}
