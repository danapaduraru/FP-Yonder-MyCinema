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
import ro.fiipractic.mycinema.dtos.CinemaDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.services.impl.CinemaServiceImpl;

import java.net.URISyntaxException;

public class CinemaControllerTest {

    @Mock
    // mock one of the implementations (because CinemaService = interface)
    private CinemaServiceImpl cinemaService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CinemaController cinemaController;

    private CinemaDto cinemaDto = new CinemaDto();
    private Cinema cinema = new Cinema();

    @Before
    // apeleaza de 3 ori metoda
    // every time before a test, initialize dependencies
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cinemaDto = new CinemaDto();
        cinemaDto.setId(1L);
        cinemaDto.setAddress("address");
        cinemaDto.setName("Luceafarul");

        cinema = new Cinema();
        cinema.setId(1L);
        cinema.setAddress("address");
        cinema.setName("Luceafarul");
    }

    // good practice : describe what it should do
    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(cinemaService.saveCinema(cinema)).thenReturn(cinema);
        Mockito.when(modelMapper.map(cinemaDto,Cinema.class)).thenReturn(cinema);
        // act
        ResponseEntity<Cinema> cinemaResponseEntity = cinemaController.saveCinema(cinemaDto);
        // assert
        Assertions.assertThat(cinemaResponseEntity).isNotNull();
        Assertions.assertThat(cinemaResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(cinemaResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(cinema); // not using isEqualTo because .equals can be overriden
        Assertions.assertThat(cinemaResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/cinemas/" + cinema.getId());
    }

    @After
    // deferentiate objects (set null), delete inserts from DB for example
    public void tearDown(){
        cinemaDto = null;
        cinema = null;
    }
}
