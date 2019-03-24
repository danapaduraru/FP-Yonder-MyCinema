package ro.fiipractic.mycinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.services.CinemaService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public List<Cinema> getAllCinemas(){
        return cinemaService.getAll();
    }

    @PostMapping

    public ResponseEntity<Cinema> saveCinema(){
        return null;
    }
}
