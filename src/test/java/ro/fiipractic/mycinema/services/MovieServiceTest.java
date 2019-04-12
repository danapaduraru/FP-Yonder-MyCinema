package ro.fiipractic.mycinema.services;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRepository;
import ro.fiipractic.mycinema.services.impl.MovieServiceImpl;

import java.util.Optional;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Movie movie = new Movie();
        movie.setId(1L);
    }

    @Test
    public void shouldReturnMovieById() throws NotFoundException {
        Mockito.when(movieRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(movie));
        Movie movieById = movieService.getMovieById(1L);
        Assertions.assertThat(movieById).isNotNull();
    }

    @Test(expected = ro.fiipractic.mycinema.exceptions.NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenMovieById() throws NotFoundException {
        Mockito.when(movieRepository.findById(0L)).thenReturn(Optional.empty());
        Movie movieById = movieService.getMovieById(0L);
    }

    @After
    public void tearDown() {
        movie = null;
    }
}
