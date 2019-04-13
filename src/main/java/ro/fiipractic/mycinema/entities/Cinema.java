package ro.fiipractic.mycinema.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonManagedReference
    private List<MovieRoom> movieRooms;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonManagedReference
    private List<MovieInstance> movieInstanceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieRoom> getMovieRooms() {
        return movieRooms;
    }

    public void setMovieRooms(List<MovieRoom> movieRooms) {
        this.movieRooms = movieRooms;
    }

    public List<MovieInstance> getMovieInstanceList() {
        return movieInstanceList;
    }

    public void setMovieInstanceList(List<MovieInstance> movieInstanceList) {
        this.movieInstanceList = movieInstanceList;
    }

    public void removeMovieRoom(Long id) {
        for (MovieRoom m : movieRooms)
            if (m.getId() == id) {
                this.movieRooms.remove(m);
                break;
            }
    }
}
