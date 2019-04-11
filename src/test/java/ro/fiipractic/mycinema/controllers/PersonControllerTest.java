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
import ro.fiipractic.mycinema.dtos.PersonDto;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.services.impl.PersonServiceImpl;

import java.net.URISyntaxException;

public class PersonControllerTest {

    @Mock
    private PersonServiceImpl personService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PersonController personController;

    private Person person = new Person();
    private PersonDto personDto = new PersonDto();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        person = new Person();
        person.setId(1L);
        person.setFullName("John Does");
        person.setEmail("john.does@mail.com");
        person.setPhone("12345000");

        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setFullName("John Does");
        personDto.setEmail("john.does@mail.com");
        personDto.setPhone("12345000");
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        // arrange
        Mockito.when(personService.savePerson(person)).thenReturn(person);
        Mockito.when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        // act
        ResponseEntity<Person> movieResponseEntity = personController.savePerson(personDto);
        // assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(person);
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/persons/" + person.getId());
    }

    @After
    public void tearDown() {
        person = null;
        personDto = null;
    }
}
