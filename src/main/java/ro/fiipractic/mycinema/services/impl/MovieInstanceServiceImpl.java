package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MovieInstanceServiceImpl implements MovieInstanceService {

    @Autowired
    MovieInstanceRepository movieInstanceRepository;

    private static final Logger logger = Logger.getLogger(MovieInstanceServiceImpl.class.getName());

    @Override
    public List<MovieInstance> getAllMovieInstances() {
        logger.info("MovieInstanceService getAllMovieInstances method called");
        return movieInstanceRepository.findAll();
    }

    @Override
    public MovieInstance getMovieInstanceById(Long id) {
        logger.info("MovieInstanceService getMovieInstanceById method called for id " + id);
        return movieInstanceRepository.findById(id).orElse(null);
    }

    @Override
    public MovieInstance saveMovieInstance(MovieInstance movieInstance) {
        logger.info("MovieInstanceService saveMovieInstance method called");
        return movieInstanceRepository.save(movieInstance);
    }

    @Override
    public void deleteMovieInstance(Long id) {
        logger.info("MovieInstanceService deleteMovieInstance method called for id " + id);
        movieInstanceRepository.deleteById(id);
        // should I also delete reservations ?
    }
}
