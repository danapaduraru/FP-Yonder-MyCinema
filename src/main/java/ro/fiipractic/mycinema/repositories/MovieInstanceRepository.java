package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entities.MovieInstance;

@Repository
public interface MovieInstanceRepository extends JpaRepository<MovieInstance, Long> {
}
