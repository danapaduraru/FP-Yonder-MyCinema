package ro.fiipractic.mycinema.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.services.impl.CinemaServiceImpl;

public class CinemaServicesTest {

    @Mock
    CinemaRepository cinemaRepository;

    @InjectMocks
    CinemaServiceImpl cinemaService;
}
