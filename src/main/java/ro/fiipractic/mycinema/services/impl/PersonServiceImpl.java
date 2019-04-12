package ro.fiipractic.mycinema.services.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.repositories.PersonRepository;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

    @Override
    public List<Person> getAllPersons() {
        logger.info("PersonService getAllPersons method called");
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) throws NotFoundException {
        logger.info("PersonService getPersonById method called for id " + id);
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Person with id=%s was not found.", id)));
    }

    @Override
    public Person savePerson(Person personToSave) {
        logger.info("PersonService savePerson method called");
        return personRepository.save(personToSave);
    }

    @Override
    public Person updateFullNameById(Long id, String name) {
        logger.info("PersonService updateFullNameById method called for id " + id + " with name: " + name);
        Person person = personRepository.getOne(id);
        person.setFullName(name);
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person updatedPerson) {
        logger.info("PersonService updatePerson method called");
        return personRepository.save(updatedPerson);
    }

    @Override
    public void deletePersonById(Long id) {
        logger.info("PersonService deletePersonById method called for id " + id);
        personRepository.deleteById(id);
    }
}
