package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieInstance;

import java.util.List;

public interface MovieInstanceService {

    List<MovieInstance> getAllMovieInstances();
    MovieInstance getMovieInstanceById(Long id);
    MovieInstance saveMovieInstance(MovieInstance movieInstance);
    void deleteMovieInstance(Long id);
}
