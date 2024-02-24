package com.alternova.streaming.mapper;

import com.alternova.streaming.dto.MovieDto;
import com.alternova.streaming.persistence.entity.Movie;

public class MovieMapper {

    private MovieMapper() {
    }

    public static Movie mapToMovie(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.id())
                .name(movieDto.name())
                .genre(movieDto.genre())
                .type(movieDto.type())
                .views(movieDto.views())
                .rating(movieDto.rating())
                .build();
    }

}
