package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(Long id) throws NotFoundException;

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie updatedMovie);

    void deleteMovie(Long id);
}
