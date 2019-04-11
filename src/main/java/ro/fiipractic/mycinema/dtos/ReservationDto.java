package ro.fiipractic.mycinema.dtos;

public class ReservationDto {

    private Long id;

    private Integer numberOfTickets;

    private Long customer_id;

    private Long movie_instance_id;

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

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getMovie_instance_id() {
        return movie_instance_id;
    }

    public void setMovie_instance_id(Long movie_instance_id) {
        this.movie_instance_id = movie_instance_id;
    }
}
