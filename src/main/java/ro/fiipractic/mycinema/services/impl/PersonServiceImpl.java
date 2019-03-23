package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.repositories.PersonRepository;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person savePerson(Person personToSave) {
        return personRepository.save(personToSave);
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person updateFullNameById(Long id, String name) {
        Person person = personRepository.getOne(id);
        person.setFullName(name);
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person updatedPerson) {
        return personRepository.save(updatedPerson);
    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
