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
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.services.impl.MovieInstanceServiceImpl;

import java.net.URISyntaxException;

public class MovieInstanceControllerTest {

    @Mock
    private MovieInstanceServiceImpl movieInstanceService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieInstanceController movieInstanceController;

    private MovieInstance movieInstance = new MovieInstance();
    private MovieInstanceDto movieInstanceDto = new MovieInstanceDto();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Cinema cinema = new Cinema();
        cinema.setId(1L);

        MovieRoom movieRoom = new MovieRoom();
        movieRoom.setId(1L);

        Movie movie = new Movie();
        movie.setId(1L);

        movieInstance = new MovieInstance();
        movieInstance.setId(1L);
        movieInstance.setAvailableSeats(35);
        movieInstance.setCinema(cinema);
        movieInstance.setMovie_room(movieRoom);
        movieInstance.setMovie(movie);
        movieInstance.setStartDate("4/11/2019 6PM");
        movieInstance.setEndDate("4/11/2019 8PM");

        movieInstanceDto = new MovieInstanceDto();
        movieInstanceDto.setId(1L);
        movieInstanceDto.setAvailableSeats(35);
        movieInstanceDto.setCinema_id(cinema.getId());
        movieInstanceDto.setMovie_room_id(movieRoom.getId());
        movieInstanceDto.setMovie_id(movie.getId());
        movieInstanceDto.setStartDate("4/11/2019 6PM");
        movieInstanceDto.setEndDate("4/11/2019 8PM");
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(movieInstanceService.saveMovieInstance(movieInstance)).thenReturn(movieInstance);
        Mockito.when(modelMapper.map(movieInstanceDto, MovieInstance.class)).thenReturn(movieInstance);
        // act
        ResponseEntity<MovieInstance> movieResponseEntity = movieInstanceController.saveMovieInstance(movieInstanceDto);
        // assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(movieInstance);
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/movie-instances/" + movieInstance.getId());
    }

    @After
    public void tearDown() {
        movieInstance = null;
        movieInstanceDto = null;
    }
}
