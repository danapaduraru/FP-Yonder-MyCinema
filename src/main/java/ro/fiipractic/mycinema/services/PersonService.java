package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import ro.fiipractic.mycinema.entities.Person;

import java.util.List;

public interface PersonService {

    Person savePerson(Person personToSave);
    Person getPersonById(Long id) throws NotFoundException;
    List<Person> getAllPersons();
    Person updateFullNameById(Long id, String name);
    Person updatePerson(Person updatedPerson);
    void deletePersonById(Long id);
}
