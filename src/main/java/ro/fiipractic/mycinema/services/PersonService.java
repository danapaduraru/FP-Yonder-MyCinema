package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Person;

public interface PersonService {

    String helloFromService();
    Person savePerson(Person personToSave);
}
