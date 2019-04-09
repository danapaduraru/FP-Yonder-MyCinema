package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.util.List;

@Service
public class MovieInstanceServiceImpl implements MovieInstanceService {

    @Autowired
    MovieInstanceRepository movieInstanceRepository;

    @Override
    public List<MovieInstance> getAllMovieInstaces() {
        return movieInstanceRepository.findAll();
    }

    @Override
    public MovieInstance getMovieInstanceById(Long id) {
        return movieInstanceRepository.findById(id).orElse(null);
    }

    @Override
    public MovieInstance saveMovieInstance(MovieInstance movieInstance) {
        return movieInstanceRepository.save(movieInstance);
    }

    @Override
    public void deleteMovieInstance(Long id) {
        movieInstanceRepository.deleteById(id);
        // probably not completed
    }
}
