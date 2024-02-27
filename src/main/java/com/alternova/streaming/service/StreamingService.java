package com.alternova.streaming.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.alternova.streaming.dto.StreamingContentDto;
import com.alternova.streaming.dto.UserRequest;
import com.alternova.streaming.persistence.entity.StreamingContent;

public interface StreamingService {

    StreamingContentDto getRandomMovie();

    StreamingContentDto getRandomSerie();

    List<StreamingContentDto> findAllMoviesSortBy(Sort sort);

    List<StreamingContentDto> findAllSeriesSortBy(Sort sort);

    List<StreamingContentDto> findContentFilterBy(String field, String value);

    StreamingContentDto markAsViewed(UserRequest request);

    StreamingContentDto rateContent(UserRequest request);

    List<StreamingContentDto> findAll(Sort sort);

}
