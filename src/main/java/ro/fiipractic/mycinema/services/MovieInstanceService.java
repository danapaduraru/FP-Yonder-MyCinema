package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface MovieInstanceService {

    List<MovieInstance> getAllMovieInstances();

    MovieInstance getMovieInstanceById(Long id) throws NotFoundException;

    List<MovieInstance> getMovieInstanceByStartDate(String startDate);

    MovieInstance saveMovieInstance(MovieInstance movieInstance);

    void deleteMovieInstance(Long id);
}
