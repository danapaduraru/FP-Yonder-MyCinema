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
import ro.fiipractic.mycinema.dtos.MovieDto;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.services.MovieService;
import ro.fiipractic.mycinema.services.impl.MovieServiceImpl;

import java.net.URISyntaxException;

public class MovieControllerTest {

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieController movieController;

    private Movie movie = new Movie();
    private MovieDto movieDto = new MovieDto();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        movie = new Movie();
        movie.setId(1L);
        movie.setDescription("This is a movie");
        movie.setDurationMinutes(120);
        movie.setTitle("Movie Title");

        movieDto = new MovieDto();
        movieDto.setId(1L);
        movieDto.setDescription("This is a movie");
        movieDto.setDurationMinutes(120);
        movieDto.setTitle("Movie Title");
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(movieService.saveMovie(movie)).thenReturn(movie);
        Mockito.when(modelMapper.map(movieDto, Movie.class)).thenReturn(movie);
        // act
        ResponseEntity<Movie> movieResponseEntity = movieController.saveMovie(movieDto);
        // assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(movie);
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/movies/" + movie.getId());
    }

    @After
    public void tearDown() {
        movie = null;
        movieDto = null;
    }

}
