package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.repositories.ReservationRepository;
import ro.fiipractic.mycinema.services.impl.ReservationServiceImpl;

import java.util.Optional;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservation = new Reservation();
        reservation.setId(1L);
    }

    @Test
    public void shouldReturnReservationById() throws ro.fiipractic.mycinema.exceptions.NotFoundException {
        Mockito.when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(reservation));
        Reservation reservationById = reservationService.getReservationById(1L);
        Assertions.assertThat(reservationById).isNotNull();
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenReservationById() throws ro.fiipractic.mycinema.exceptions.NotFoundException {
        Mockito.when(reservationRepository.findById(0L)).thenReturn(Optional.empty());
        Reservation reservationById = reservationService.getReservationById(0L);
    }

    @After
    public void tearDown() {
        reservation = null;
    }
}
