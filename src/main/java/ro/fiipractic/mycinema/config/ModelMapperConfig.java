package ro.fiipractic.mycinema.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.dtos.MovieRoomDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.MovieRoom;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.typeMap(MovieRoomDto.class, MovieRoom.class).addMappings(m -> {
            m.<Long>map(MovieRoomDto::getCinema_id, (MovieRoom, v) -> MovieRoom.getCinema().setId(v));
        });

        modelMapper.typeMap(MovieInstanceDto.class, MovieInstance.class).addMappings(m -> {
            m.<Long>map(MovieInstanceDto::getMovie_id, (dto, id) -> dto.getMovie().setId(id));
        });

        modelMapper.typeMap(MovieInstance.class, MovieInstanceDto.class).addMappings(m -> {
            m.<Integer>map(entity -> entity.getMovie().getId(), (MovieInstanceDto, v) -> MovieInstanceDto.setMovie_id(v));
        });

        return modelMapper;
    }
}
