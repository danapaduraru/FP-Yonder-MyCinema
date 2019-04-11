package ro.fiipractic.mycinema.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.impl.MovieRoomServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRoomServiceTest {

    @Mock
    MovieRoomRepository movieRoomRepository;

    @InjectMocks
    MovieRoomServiceImpl movieRoomService;

    private List<MovieRoom> movieRooms = new ArrayList<>();
    private Cinema cinema = new Cinema();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cinema.setId(2L);
        MovieRoom movieRoom = new MovieRoom();
        movieRoom.setId(2L);
        movieRoom.setCinema(cinema);
        movieRooms.add(movieRoom);
    }

    @Test
    public void shouldReturnMovieRoomByCinemaId() {
        // arrange
        Mockito.when(movieRoomRepository.getMovieRoomsByCinema_Id(2L)).thenReturn((List<MovieRoom>) movieRooms);
        // act
        List<MovieRoom> movieRoomsByCinemaId = movieRoomService.getAllMovieRoomsByCinemaId(2L);
        // assert
        Assertions.assertThat(movieRoomsByCinemaId).isNotNull();
    }

    @Test
    public void shouldReturnEmptyMovieRoomListWhenMovieRoomByCinemaId() {
        Mockito.when(movieRoomRepository.getMovieRoomsByCinema_Id(0L)).thenReturn(Collections.emptyList());
        List<MovieRoom> movieRoomsByCinemaId = movieRoomService.getAllMovieRoomsByCinemaId(0L);
        Assertions.assertThat(movieRoomsByCinemaId).isEmpty();
    }
}
