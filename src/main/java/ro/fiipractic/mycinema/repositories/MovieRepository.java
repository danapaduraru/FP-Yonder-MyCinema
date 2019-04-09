package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
