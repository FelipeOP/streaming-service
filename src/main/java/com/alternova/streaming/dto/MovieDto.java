package com.alternova.streaming.dto;

import com.alternova.streaming.persistence.constants.MovieType;

public record MovieDto(
        Long id,
        String name,
        String genre,
        MovieType type,
        Long views,
        Float rating
) {
}
