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
import ro.fiipractic.mycinema.dtos.ReservationDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.services.impl.ReservationServiceImpl;

import java.net.URISyntaxException;

public class ReservationControllerTest {

    @Mock
    private ReservationServiceImpl reservationService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation reservation = new Reservation();
    private ReservationDto reservationDto = new ReservationDto();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Person person = new Person();
        person.setId(1L);

        MovieInstance movieInstance = new MovieInstance();
        movieInstance.setId(1L);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setNumberOfTickets(2);
        reservation.setPerson(person);
        reservation.setMovieInstance(movieInstance);

        reservationDto = new ReservationDto();
        reservationDto.setId(1L);
        reservationDto.setNumberOfTickets(2);
        reservationDto.setCustomer_id(person.getId());
        reservationDto.setMovie_instance_id(movieInstance.getId());
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(reservationService.saveReservation(reservation)).thenReturn(reservation);
        Mockito.when(modelMapper.map(reservationDto, Reservation.class)).thenReturn(reservation);
        // act
        ResponseEntity<Reservation> movieResponseEntity = reservationController.saveReservation(reservationDto);
        // assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(reservation);
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/reservations/" + reservation.getId());
    }

    @After
    public void tearDown() {
        reservation = null;
        reservationDto = null;
    }
}
