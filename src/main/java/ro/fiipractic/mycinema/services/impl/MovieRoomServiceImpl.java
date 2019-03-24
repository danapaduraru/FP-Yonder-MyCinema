package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.util.List;

@Service
public class MovieRoomServiceImpl implements MovieRoomService {

    @Autowired
    MovieRoomRepository movieRoomRepository;

    @Override
    public List<MovieRoom> getAll() {
        return null;
    }

    @Override
    public MovieRoom saveMovieRoom(MovieRoom movieRoom) {
        return null;
    }
}
