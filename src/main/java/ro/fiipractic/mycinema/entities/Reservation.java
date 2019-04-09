package ro.fiipractic.mycinema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_tickets")
    private Integer numberOfTickets;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Person person;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_instance_id")
    private MovieInstance movieInstance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
