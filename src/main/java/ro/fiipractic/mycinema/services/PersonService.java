package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Person;

import java.util.List;

public interface PersonService {

    Person savePerson(Person personToSave);
    Person getPerson(Long id);
    List<Person> getAllPersons();
    Person updateFullNameById(Long id, String name);
    void updatePerson(Person updatedPerson);
    void deletePersonById(Long id);
}
