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
}
