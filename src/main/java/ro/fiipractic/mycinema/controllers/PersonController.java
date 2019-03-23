package ro.fiipractic.mycinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/save")
    public Person saveMyPerson(@RequestBody Person personForSave) {
        return personService.savePerson(personForSave);
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @GetMapping(value = "/getAll")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PatchMapping(value = "/updateName")
    public Person updateFullName(@RequestParam Long id, @RequestParam String name) {
        return personService.updateFullNameById(id, name);
    }

    @PutMapping(value = "/update")
    public void updatePerson(@RequestBody Person updatedPerson) {
        personService.updatePerson(updatedPerson);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
    }
}