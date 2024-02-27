package com.alternova.streaming.dto;

import java.util.Set;

import com.alternova.streaming.persistence.entity.StreamingMetadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class StreamingContentDto {
    private Long id;
    private String name;
    private String genre;
    private String type;
    private Long views;
    private Float rating;
    private Set<StreamingMetadata> streamingMetadata;
}