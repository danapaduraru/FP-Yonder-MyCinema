package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRepository;
import ro.fiipractic.mycinema.services.MovieService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    private static final Logger logger = Logger.getLogger(MovieServiceImpl.class.getName());

    @Override
    public List<Movie> getAllMovies() {
        logger.info("MovieService getAllMovies method called");
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws NotFoundException {
        logger.info("MovieService getMovieById method called for id " + id);
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Movie with id=%s was not found.", id)));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        logger.info("MovieService saveMovie method called");
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        logger.info("MovieService deleteMovie method called for id " + id);
        movieRepository.deleteById(id);
    }
}
