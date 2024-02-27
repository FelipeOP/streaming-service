package com.alternova.streaming.mapper;

import com.alternova.streaming.dto.StreamingContentDto;
import com.alternova.streaming.persistence.entity.StreamingContent;

public class StreamingContentMapper {

    private StreamingContentMapper() {
    }

    public static StreamingContent mapToEntity(StreamingContentDto streamingContentDto) {
        return StreamingContent.builder()
                .id(streamingContentDto.getId())
                .name(streamingContentDto.getName())
                .genre(streamingContentDto.getGenre())
                .type(streamingContentDto.getType())
                .views(streamingContentDto.getViews())
                .rating(streamingContentDto.getRating())
                .streamingMetadata(streamingContentDto.getStreamingMetadata())
                .build();
    }

    public static StreamingContentDto mapToDto(StreamingContent streamingContent) {
        return new StreamingContentDto(
                streamingContent.getId(),
                streamingContent.getName(),
                streamingContent.getGenre(),
                streamingContent.getType(),
                streamingContent.getViews(),
                streamingContent.getRating(),
                streamingContent.getStreamingMetadata());

    }

}
