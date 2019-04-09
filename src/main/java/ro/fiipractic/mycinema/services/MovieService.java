package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
}
