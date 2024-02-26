package com.alternova.streaming.dto;

import com.alternova.streaming.persistence.constants.StreamingType;

public record StreamingContentDto(
        Long id,
        String name,
        String genre,
        StreamingType type,
        Long views,
        Float rating
) {
}
