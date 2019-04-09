package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie saveMovie(Movie movie);
    void deleteMovie(Long id);
}
