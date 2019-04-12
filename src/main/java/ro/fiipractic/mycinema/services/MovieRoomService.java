package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface MovieRoomService {

    List<MovieRoom> getAllMovieRooms();

    MovieRoom getMovieRoomById(Long id) throws NotFoundException;

    MovieRoom saveMovieRoom(MovieRoom movieRoom);

    List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId);

    void deleteMovieRoom(Long id);
}
