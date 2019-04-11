package ro.fiipractic.mycinema.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.impl.MovieInstanceServiceImpl;

public class MovieInstanceServiceTest {

    @Mock
    MovieInstanceRepository movieInstanceRepository;

    @InjectMocks
    MovieInstanceServiceImpl movieInstanceService;

    MovieInstance movieInstance;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieInstance = new MovieInstance();
        movieInstance.setId(5L);
    }

    @Test
    public void shouldReturnMovieInstanceById() {
        // arrange
        Mockito.when(movieInstanceRepository.findById(5L)).thenReturn(java.util.Optional.ofNullable(movieInstance));
        // act
        MovieInstance movieInstanceById = movieInstanceService.getMovieInstanceById(5L);
        // assert
        Assertions.assertThat(movieInstanceById).isNotNull();
    }

    @Test
    public void shouldReturnNullMovieInstanceObjectWhenMovieInstaceById() {
        // arrange
        Mockito.when(movieInstanceRepository.findById(0L)).thenReturn(java.util.Optional.empty());
        // act
        MovieInstance movieInstanceById = movieInstanceService.getMovieInstanceById(0L);
        // assert
        Assertions.assertThat(movieInstanceById).isNull();
    }
}