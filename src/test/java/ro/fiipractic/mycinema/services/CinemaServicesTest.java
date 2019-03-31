package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.services.impl.CinemaServiceImpl;

public class CinemaServicesTest {

    @Mock
    CinemaRepository cinemaRepository;

    @InjectMocks
    CinemaServiceImpl cinemaService;

    Cinema cinema;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cinema = new Cinema();
        cinema.setId(1L);
        cinema.setName("Cinema 1");
        cinema.setAddress("cinema's address");
    }

    @Test
    public void shouldReturnCinemaById() throws NotFoundException {
        // arrange
        Mockito.when(cinemaRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(cinema));
        // act
        Cinema cinemaById = cinemaService.getCinemaById(1L);

        // assert
        Assertions.assertThat(cinemaById).isNotNull();
    }

    @Test(expected = NotFoundException.class) // so that app does not crash when test crashes
    public void shouldThrowNotFoundExceptionWhenCinemaById() throws NotFoundException {
        // arrange
        Mockito.when(cinemaRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        // act
        Cinema cinemaById = cinemaService.getCinemaById(2L);
        // assert
    }
}
