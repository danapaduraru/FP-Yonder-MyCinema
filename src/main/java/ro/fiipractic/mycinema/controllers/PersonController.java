package ro.fiipractic.mycinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.services.PersonService;

import javax.validation.constraints.PastOrPresent;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping(value = "/hi")
    public String sayHei() {
        return "Hei";
    }

    @PostMapping(value = "/post/{number}")
    public String getMyNumber(@PathVariable Integer number) {
        return "Your number is " + number;
    }

    @PostMapping(value = "/post/")
    public String getName(@RequestBody String name) {
        return "Your name is: " + name;
    }

    @PostMapping(value = "/postnumber/")
    public String getNumber(@RequestParam Integer number) {
        return "Your RequestParam number is: " + number;
    }

    @PostMapping(value = "/post/person/")
    public String getPersonDetails(@RequestBody Person person) {
        return "Hi, this is " + person.getName() + " " + person.getSurname() + " with ID " + person.getId();
    }

    @GetMapping(value = "/helloservice")
    public String sayHelloFromService() {
        return personService.helloFromService();
    }
}