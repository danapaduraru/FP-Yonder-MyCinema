package ro.fiipractic.mycinema.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.dtos.MovieRoomDto;
import ro.fiipractic.mycinema.dtos.ReservationDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.entities.Reservation;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // MovieRoomDto -> MovieRoom
        modelMapper.typeMap(MovieRoomDto.class, MovieRoom.class).addMappings(m -> {
            m.<Long>map(MovieRoomDto::getCinema_id, (MovieRoom, v) -> MovieRoom.getCinema().setId(v));
        });

        // MovieInstanceDto -> MovieInstance
        modelMapper.typeMap(MovieInstanceDto.class, MovieInstance.class).addMappings(m -> {
            m.<Long>map(MovieInstanceDto::getMovie_id, (dto, id) -> dto.getMovie().setId(id));
        });

        modelMapper.typeMap(MovieInstanceDto.class, MovieInstance.class).addMappings(m -> {
            m.<Long>map(MovieInstanceDto::getMovie_room_id, (dto, id) -> dto.getMovie_room().setId(id));
        });

        modelMapper.typeMap(MovieInstanceDto.class, MovieInstance.class).addMappings(m -> {
            m.<Long>map(MovieInstanceDto::getCinema_id, (dto, id) -> dto.getCinema().setId(id));
        });

        modelMapper.typeMap(MovieInstanceDto.class, MovieInstance.class).addMappings(m -> {
            m.<Long>map(MovieInstanceDto::getCinema_id, (dto, id) -> dto.getCinema().setId(id));
        });


        // MovieInstance -> MovieInstanceDto
        modelMapper.typeMap(MovieInstance.class, MovieInstanceDto.class).addMappings(m -> {
            m.<Long>map(entity -> entity.getMovie().getId(), (MovieInstanceDto, v) -> MovieInstanceDto.setMovie_id(v));
        });

        modelMapper.typeMap(MovieInstance.class, MovieInstanceDto.class).addMappings(m -> {
            m.<Long>map(entity -> entity.getMovie_room().getId(), (MovieInstanceDto, v) -> MovieInstanceDto.setMovie_room_id(v));
        });

        modelMapper.typeMap(MovieInstance.class, MovieInstanceDto.class).addMappings(m -> {
            m.<Long>map(entity -> entity.getCinema().getId(), (MovieInstanceDto, v) -> MovieInstanceDto.setCinema_id(v));
        });

        // ReservationDto -> Reservation
        modelMapper.typeMap(ReservationDto.class, Reservation.class).addMappings(m -> {
            m.<Long>map(ReservationDto::getCustomer_id, (dto, id) -> dto.getPerson().setId(id));
        });

        modelMapper.typeMap(ReservationDto.class, Reservation.class).addMappings(m -> {
            m.<Long>map(ReservationDto::getMovie_instance_id, (dto, id) -> dto.getMovieInstance().setId(id));
        });

        // Reservation -> ReservationDto
        modelMapper.typeMap(Reservation.class, ReservationDto.class).addMappings(m -> {
            m.<Long>map(entity -> entity.getPerson().getId(), (ReservationDto, v) -> ReservationDto.setCustomer_id(v));
        });

        modelMapper.typeMap(Reservation.class, ReservationDto.class).addMappings(m -> {
            m.<Long>map(entity -> entity.getMovieInstance().getId(), (ReservationDto, v) -> ReservationDto.setMovie_instance_id(v));
        });
        return modelMapper;
    }
}
