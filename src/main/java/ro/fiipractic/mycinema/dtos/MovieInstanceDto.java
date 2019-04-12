package ro.fiipractic.mycinema.dtos;

public class MovieInstanceDto {

    private Long id;

    private String startDate;

    private String endDate;

    private Integer availableSeats;

    private Long cinema_id;

    private Long movie_id;

    private Long movie_room_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Long cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Long getMovie_room_id() {
        return movie_room_id;
    }

    public void setMovie_room_id(Long movie_room_id) {
        this.movie_room_id = movie_room_id;
    }

    @Override
    public String toString() {
        return "MovieInstanceDto{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", availableSeats=" + availableSeats +
                ", cinema_id=" + cinema_id +
                ", movie_id=" + movie_id +
                ", movie_room_id=" + movie_room_id +
                '}';
    }
}
