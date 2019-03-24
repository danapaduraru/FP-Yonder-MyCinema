package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entities.MovieRoom;

import java.util.List;

@Repository
public interface MovieRoomRepository extends JpaRepository<MovieRoom, Long> {

    List<MovieRoom> getMovieRoomsByCinema_Id (Long cinemaId);
}
