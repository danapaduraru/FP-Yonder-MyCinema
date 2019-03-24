package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entities.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
