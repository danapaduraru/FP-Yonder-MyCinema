package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Person;

import java.util.List;

public interface PersonService {

    String helloFromService();
    Person savePerson(Person personToSave);
    Person getPerson(Long id);
    List<Person> getAllPersons();
}
