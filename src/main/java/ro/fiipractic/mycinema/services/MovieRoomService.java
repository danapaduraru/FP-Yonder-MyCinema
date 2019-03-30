package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieRoom;

import java.util.List;

public interface MovieRoomService {

    List<MovieRoom> getAll();

    MovieRoom saveMovieRoom(MovieRoom movieRoom);

    List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId);

    void deleteMovieRoom(Long id);
}
