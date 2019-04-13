package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.util.ArrayList;
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
    public MovieInstance getMovieInstanceById(Long id) throws NotFoundException {
        logger.info("MovieInstanceService getMovieInstanceById method called for id " + id);
        return movieInstanceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("MovieInstance with id=%s was not found.", id)));
    }

    @Override
    public List<MovieInstance> getMovieInstanceByStartDate(String startDate) {
        logger.info("MovieInstanceService getMovieInstanceByStartDate method called for startDate " + startDate);
        startDate = startDate.substring(0,10); // get only date without hour
        String movieStartDate = "";
        List<MovieInstance> movieInstancesToday = new ArrayList<>();

        for(MovieInstance movieInstance : getAllMovieInstances()) {
            movieStartDate = movieInstance.getStartDate().substring(0,10);
            if(movieStartDate.equals(startDate)) {
                movieInstancesToday.add(movieInstance);
            }
        }
        return movieInstancesToday;
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
    }
}
