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
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.repositories.PersonRepository;
import ro.fiipractic.mycinema.services.impl.PersonServiceImpl;

import java.util.Optional;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        person = new Person();
        person.setId(1L);
    }

    @Test
    public void shouldReturnPersonById() throws NotFoundException {
        // arrange
        Mockito.when(personRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(person));
        // act
        Person personById = personService.getPersonById(1L);
        // assert
        Assertions.assertThat(personById).isNotNull();
    }

    @Test(expected = NotFoundException.class) // so that app does not crash when test crashes
    public void shouldThrowNotFoundExceptionWhenPersonById() throws NotFoundException {
        Mockito.when(personRepository.findById(0L)).thenReturn(Optional.empty());
        Person personById = personService.getPersonById(0L);
    }

    @After
    public void tearDown() {
        person = null;
    }
}
