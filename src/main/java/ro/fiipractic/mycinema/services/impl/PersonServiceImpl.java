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
    public Person getPerson(Long id) {
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
    public void updatePerson(Person updatedPerson) {
        Person person = personRepository.getOne(updatedPerson.getId());
        person.setFullName(updatedPerson.getFullName());
        person.setEmail(updatedPerson.getEmail());
        person.setPhone(updatedPerson.getPhone());
        savePerson(person);
    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
