package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.MovieRoomService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MovieRoomServiceImpl implements MovieRoomService {

    @Autowired
    private MovieRoomRepository movieRoomRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    private static final Logger logger = Logger.getLogger(MovieRoomServiceImpl.class.getName());

    @Override
    public List<MovieRoom> getAllMovieRooms() {
        logger.info("MovieRoomService getAllMovieRooms method called");
        return movieRoomRepository.findAll();
    }

    @Override
    public MovieRoom getMovieRoomById(Long id) throws NotFoundException {
        logger.info("MovieRoomService getMovieRoomById method called for id " + id);
        return movieRoomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("MovieRoom with id=%s was not found.", id)));
    }

    @Override
    public List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId) {
        logger.info("MovieRoomService getAllMovieRoomsByCinemaId method called for id " + cinemaId);
        return movieRoomRepository.getMovieRoomsByCinema_Id(cinemaId);
    }

    @Override
    public MovieRoom saveMovieRoom(MovieRoom movieRoom) {
        logger.info("MovieRoomService saveMovieRoom method called");
        return movieRoomRepository.save(movieRoom);
    }

    @Override
    public MovieRoom updateMovieRoom(MovieRoom updatedMovieRoom) {
        logger.info("MovieRoomService updateMovieRoom method called");
        return movieRoomRepository.save(updatedMovieRoom);
    }

    @Override
    public void deleteMovieRoom(Long id) {
        logger.info("MovieRoomService deleteMovieRoom method called for id " + id);
        MovieRoom movieRoom = Optional.ofNullable(movieRoomRepository.getOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Movie room with id " + id + " was not found"));

        Cinema cinema = Optional.ofNullable(cinemaRepository.getOne(movieRoom.getCinema().getId()))
                .orElseThrow(() -> new EntityNotFoundException("Cinema with id " + id + " was not found"));

        cinema.removeMovieRoom(id);
        cinemaRepository.save(cinema);
    }
}
