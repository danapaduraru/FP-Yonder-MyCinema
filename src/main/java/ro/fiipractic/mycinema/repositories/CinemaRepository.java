package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entities.Cinema;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    // HQL
    @Query("SELECT c FROM Cinema c JOIN c.movieRooms WHERE capacity = :capacity")
    List<Cinema> getCinemasByMovieRoomCapacity(@Param("capacity") Integer capacity);
}
