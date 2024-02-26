package com.alternova.streaming.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alternova.streaming.dto.StreamingContentDto;
import com.alternova.streaming.dto.UserRequest;
import com.alternova.streaming.mapper.StreamingContentMapper;
import com.alternova.streaming.persistence.constants.StreamingType;
import com.alternova.streaming.persistence.entity.StreamingContent;
import com.alternova.streaming.persistence.entity.StreamingMetadata;
import com.alternova.streaming.persistence.entity.User;
import com.alternova.streaming.persistence.repository.StreamingMetadataRepository;
import com.alternova.streaming.persistence.repository.StreamingRepository;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StreamingServiceImpl implements StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMetadataRepository metadataRepository;
    private final EntityManager entityManager;

    @Override
    public StreamingContentDto getRandomMovie() {
        return streamingRepository.selectRandomContentByType(StreamingType.MOVIE);
    }

    @Override
    public StreamingContentDto getRandomSerie() {
        return streamingRepository.selectRandomContentByType(StreamingType.SERIE);
    }

    @Override
    public List<StreamingContentDto> findAllMoviesSortBy(Sort sort) {
        return streamingRepository
                .findAllByType(StreamingType.MOVIE, sort)
                .stream()
                .map(StreamingContentMapper::mapToDto)
                .toList();
    }

    @Override
    public List<StreamingContentDto> findAllSeriesSortBy(Sort sort) {
        return streamingRepository
                .findAllByType(StreamingType.SERIE, sort)
                .stream()
                .map(StreamingContentMapper::mapToDto)
                .toList();
    }

    @Override
    public List<StreamingContentDto> findContentFilterBy(
            String field,
            String value) throws IllegalArgumentException {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(StreamingContentDto.class);
        var root = query.from(StreamingContent.class);

        query.select(builder.construct(
                StreamingContentDto.class,
                root.get("id"),
                root.get("name"),
                root.get("genre"),
                root.get("type"),
                root.get("views"),
                root.get("rating")));

        query.where(builder.like(builder.lower(root.get(field)), "%" + value.toLowerCase() + "%"));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public StreamingContentDto markAsViewed(UserRequest request) {
        var content = streamingRepository
                .findById(request.getContentId())
                .orElseThrow();

        metadataRepository
                .findByUserIdAndStreamingContentId(request.getUserId(), request.getContentId())
                .ifPresentOrElse(metadata -> {
                    metadata.setViewed(request.isViewed());
                    metadataRepository.save(metadata);
                }, () -> {
                    request.setScore(0);
                    var newMetadata = saveMetadata(content, request);
                    content.setStreamingMetadata(Collections.singleton(newMetadata));
                });

        var count = metadataRepository.countViewsByContentId(request.getContentId());
        content.setViews(count);

        return StreamingContentMapper.mapToDto(streamingRepository.save(content));
    }

    @Override
    public StreamingContentDto rateContent(UserRequest request) {
        var content = streamingRepository
                .findById(request.getContentId())
                .orElseThrow();

        metadataRepository
                .findByUserIdAndStreamingContentId(request.getUserId(), request.getContentId())
                .ifPresentOrElse(metadata -> {
                    metadata.setScore(request.getScore());
                    metadataRepository.save(metadata);
                }, () -> {
                    request.setViewed(true);
                    var newMetadata = saveMetadata(content, request);
                    content.setStreamingMetadata(Collections.singleton(newMetadata));
                });

        var average = metadataRepository
                .averageScoreByContentId(request.getContentId())
                .orElse(0f);
        content.setRating(average);

        return StreamingContentMapper.mapToDto(streamingRepository.save(content));
    }

    private StreamingMetadata saveMetadata(StreamingContent content, UserRequest request) {
        var metadata = new StreamingMetadata();
        metadata.setId(null);
        metadata.setStreamingContent(content);
        metadata.setScore(request.getScore());
        metadata.setViewed(request.isViewed());
        metadata.setUser(new User(request.getUserId(), Collections.singleton(metadata)));
        return metadataRepository.save(metadata);
    }

}
