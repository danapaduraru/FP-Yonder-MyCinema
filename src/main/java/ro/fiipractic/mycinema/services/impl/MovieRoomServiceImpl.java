package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.MovieRoomService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieRoomServiceImpl implements MovieRoomService {

    @Autowired
    private MovieRoomRepository movieRoomRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<MovieRoom> getAll() {
        return movieRoomRepository.findAll();
    }

    @Override
    public MovieRoom saveMovieRoom(MovieRoom movieRoom) {
        return movieRoomRepository.save(movieRoom);
    }

    @Override
    public List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId) {
        return movieRoomRepository.getMovieRoomsByCinema_Id(cinemaId);
    }

    @Override
    public void deleteMovieRoom(Long id) {
        //movieRoomRepository.deleteById(id);
        MovieRoom movieRoom = Optional.ofNullable(movieRoomRepository.getOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Movie room with id " + id + " was not found"));

        Cinema cinema = Optional.ofNullable(cinemaRepository.getOne(movieRoom.getCinema().getId()))
                .orElseThrow(() -> new EntityNotFoundException("Cinema with id " + id + " was not found"));

        cinema.removeMovieRoom(id);

        cinemaRepository.save(cinema);
    }
}
