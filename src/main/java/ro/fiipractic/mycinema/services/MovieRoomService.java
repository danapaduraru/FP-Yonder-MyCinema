package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface MovieRoomService {

    List<MovieRoom> getAllMovieRooms();

    MovieRoom getMovieRoomById(Long id) throws NotFoundException;

    List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId);

    MovieRoom saveMovieRoom(MovieRoom movieRoom);

    MovieRoom updateMovieRoom(MovieRoom updatedMovieRoom);

    void deleteMovieRoom(Long id);
}
