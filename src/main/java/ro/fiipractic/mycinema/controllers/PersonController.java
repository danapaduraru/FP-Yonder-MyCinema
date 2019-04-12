package ro.fiipractic.mycinema.controllers;

import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.PersonDto;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.services.PersonService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(PersonController.class.getName());

    @GetMapping()
    public List<PersonDto> getAllPersons() {
        logger.info("PersonController getAllPersons method called");
        List<PersonDto> personDtos = new ArrayList<>();

        for (Person entity : personService.getAllPersons()) {
            PersonDto map = modelMapper.map(entity, PersonDto.class);
            personDtos.add(map);
        }
        return personDtos;
    }

    @GetMapping(value = "/{id}")
    public PersonDto getPerson(@PathVariable Long id) throws NotFoundException {
        logger.info("PersonController getPerson method called with id " + id);
        Person entity = personService.getPersonById(id);

        return modelMapper.map(entity, PersonDto.class);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody PersonDto personDto) throws URISyntaxException {
        logger.info("PersonController savePerson method called for person " + personDto.toString());
        Person person = personService.savePerson(modelMapper.map(personDto, Person.class));
        return ResponseEntity.created(new URI("/api/persons/" + person.getId())).body(person);
    }

    @PatchMapping(value = "/updateName")
    public Person updateFullName(@RequestParam Long id, @RequestParam String name) {
        logger.info("PersonController updateFullName method called with id " + id + " and name " + name);
        return personService.updateFullNameById(id, name);
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personToUpdate) throws NotFoundException, BadRequestException {
        logger.info("PersonController updatePerson method called for person " + personToUpdate.toString());
        if(!id.equals(personToUpdate.getId())){
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + personToUpdate.getId() + " from RequestBody");
        }
        Person personDb = personService.getPersonById(id);
        return personService.updatePerson(personToUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        logger.info("PersonController deletePerson method called with id " + id);
        personService.deletePersonById(id);
    }
}