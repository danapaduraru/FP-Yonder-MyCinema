package ro.fiipractic.mycinema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "movie_instance")
public class MovieInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_room_id")
    private MovieRoom movieRoom;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "available_seats")
    private Integer availableSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieRoom getMovieRoom() {
        return movieRoom;
    }

    public void setMovieRoom(MovieRoom movieRoom) {
        this.movieRoom = movieRoom;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
