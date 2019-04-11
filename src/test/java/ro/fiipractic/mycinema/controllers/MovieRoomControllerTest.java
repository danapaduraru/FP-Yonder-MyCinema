package ro.fiipractic.mycinema.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.fiipractic.mycinema.dtos.MovieRoomDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.services.impl.MovieRoomServiceImpl;

import java.net.URISyntaxException;

public class MovieRoomControllerTest {

    @Mock
    private MovieRoomServiceImpl movieRoomService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieRoomController movieRoomController;

    private MovieRoom movieRoom = new MovieRoom();
    private MovieRoomDto movieRoomDto = new MovieRoomDto();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Cinema cinema = new Cinema();
        cinema.setId(1L);
        Movie movie = new Movie();
        movie.setId(1L);

        movieRoom = new MovieRoom();
        movieRoom.setId(1L);
        movieRoom.setCapacity(30);
        movieRoom.setName("MovieRoom1");
        movieRoom.setCinema(cinema);

        movieRoomDto = new MovieRoomDto();
        movieRoomDto.setId(1L);
        movieRoomDto.setCapacity(30);
        movieRoomDto.setName("MovieRoom1");
        movieRoomDto.setCinema_id(cinema.getId());
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(movieRoomService.saveMovieRoom(movieRoom)).thenReturn(movieRoom);
        Mockito.when(modelMapper.map(movieRoomDto, MovieRoom.class)).thenReturn(movieRoom);
        // act
        ResponseEntity<MovieRoom> movieResponseEntity = movieRoomController.saveMovieRoom(movieRoomDto);
        // assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(movieRoom);
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/movie-rooms/" + movieRoom.getId());
    }

    @After
    public void tearDown() {
        movieRoom = null;
        movieRoomDto = null;
    }
}
