package ro.fiipractic.mycinema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie_room")
public class MovieRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer capacity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cinema_id")
    private Cinema cinema; // numele field-ului de cinema care e in mappedby in Cinema

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "movie_room")
    @JsonManagedReference
    private List<MovieInstance> movieInstances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<MovieInstance> getMovieInstances() {
        return movieInstances;
    }

    public void setMovieInstances(List<MovieInstance> movieInstances) {
        this.movieInstances = movieInstances;
    }
}
